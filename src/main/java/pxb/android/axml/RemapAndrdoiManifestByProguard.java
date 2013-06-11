package pxb.android.axml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pxb.android.axml.Axml.Node;
import pxb.android.axml.AxmlVisitor.NodeVisitor;
import pxb.android.axml.RemapAndrdoiManifestByProguard.RemapNodeVisitor.Config;

public class RemapAndrdoiManifestByProguard {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> ps = new ArrayList<String>(args.length);
        boolean strip = false;
        for (String s : args) {
            if (s.equals("--strip")) {
                strip = true;
            } else {
                ps.add(s);
            }
        }
        if (ps.size() < 3) {
            System.err.println("RemapAndrdoiManifestByProguard [--strip] in.axml proguard-map.txt out.axml");
            return;
        }
        File in = new File(ps.get(0));
        File cfg = new File(ps.get(1));
        File out = new File(ps.get(2));
        Map<String, String> clzMap = new HashMap<String, String>();
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(cfg), "utf8"));
        try {
            for (String ln = r.readLine(); ln != null; ln = r.readLine()) {
                if (ln.startsWith("#") || ln.startsWith(" ")) {
                    continue;
                }
                // format a.pt.Main -> a.a.a:
                int i = ln.indexOf("->");
                if (i > 0) {
                    clzMap.put(ln.substring(0, i).trim(), ln.substring(i + 2, ln.length() - 1).trim());
                }
            }
        } finally {
            r.close();
        }

        InputStream is = new FileInputStream(in);
        byte[] xml = new byte[is.available()];
        is.read(xml);
        is.close();

        AxmlReader rd = new AxmlReader(xml);
        AxmlWriter aw = new AxmlWriter();
        AxmlVisitor av = new DumpAdapter(aw);
        if (strip) {
            av = new StripManifestAdapter(av);
        }
        final Config config = new Config(clzMap);
        rd.accept(new AxmlVisitor(av) {

            @Override
            public NodeVisitor first(String ns, String name) {
                return new RemapNodeVisitor(config, ns, name, super.first(ns, name));
            }
        });
        byte[] outBytes = aw.toByteArray();
        FileOutputStream fos = new FileOutputStream(out);
        fos.write(outBytes);
        fos.close();
    }

    public static class RemapNodeVisitor extends NodeVisitor {
        public static class Config {
            public String pkg;

            public Config(Map<String, String> classMap) {
                super();
                this.classMap = classMap;
            }

            public Map<String, String> classMap;
        }

        final Config cfg;
        String ns;
        String name;
        static Set<String> nameTags = new HashSet<String>(Arrays.asList("application", "activity", "receiver",
                "service"));

        public RemapNodeVisitor(Config cfg, String ns, String name, NodeVisitor nv) {
            super(nv);
            this.ns = ns;
            this.name = name;
            this.cfg = cfg;
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            return new RemapNodeVisitor(cfg, ns, name, super.child(ns, name));
        }

        @Override
        public void attr(String ns, String name, int resourceId, int type, Object obj) {
            if (this.ns == null || this.ns.length() == 0) {
                Config cfg = this.cfg;
                if (this.name.equals("manifest")) {
                    if ("package".equals(name) && (ns == null || ns.length() == 0)) {
                        cfg.pkg = obj.toString();
                    }
                }
                if (resourceId == 0x1010003 && nameTags.contains(this.name)) {
                    String clz = obj.toString();
                    String key = cfg.pkg + clz;
                    boolean find = false;
                    if (cfg.classMap.containsKey(key)) {
                        obj = cfg.classMap.get(key);
                        find = true;
                    }
                    if (!find) {
                        key = clz;
                        if (cfg.classMap.containsKey(key)) {
                            obj = cfg.classMap.get(key);
                            find = true;
                        }
                    }
                    if (!find) {
                        key = cfg.pkg + "." + clz;
                        if (cfg.classMap.containsKey(key)) {
                            obj = cfg.classMap.get(key);
                            find = true;
                        }
                    }
                }
            }
            super.attr(ns, name, resourceId, type, obj);
        }
    }

    static String getPackage(Axml axml) {
        for (Node n : axml.firsts) {
            if ("manifest".equals(n.name) && (n.ns == null || n.ns.length() == 0)) {
                for (Node.Attr a : n.attrs) {
                    if ("package".equals(a.name) && (a.ns == null || a.ns.length() == 0)) {
                        return a.value.toString();
                    }
                }
                return null;
            }
        }
        return null;
    }
}
