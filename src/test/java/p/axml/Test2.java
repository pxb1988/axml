package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import p.axml.n.AxmlReader;
import p.axml.n.AxmlVisitor;
import p.axml.n.AxmlVisitor.NodeVisitor;
import p.axml.n.AxmlWriter;

import com.googlecode.dex2jar.reader.io.LeArrayDataIn;

public class Test2 {
    @Test
    public void test() throws Exception {
        for (File file : new File("src/test/resources/").listFiles()) {
            if (file.getName().endsWith(".axml")) {
                InputStream is = new FileInputStream(file);
                byte[] xml = new byte[is.available()];
                is.read(xml);
                is.close();
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                AxmlReader rd = new AxmlReader(new LeArrayDataIn(xml));
                AxmlWriter wr = new AxmlWriter();
                rd.accept(new AxmlVisitor() {
                    Map<String, String> nses = new HashMap();

                    @Override
                    public void ns(String prefix, String uri, int ln) {
                        System.out.println(prefix + "=" + uri);
                        this.nses.put(uri, prefix);
                    }

                    @Override
                    public NodeVisitor first(String ns, String name) {
                        XN x = new XN(1, nses);
                        System.out.print("<");
                        if (ns != null) {
                            System.out.println(nses.get(ns) + ":");
                        }
                        System.out.println(name);
                        return x;
                    }

                    @Override
                    public void end() {
                    }
                });
                // wr.toByteArray();
            }
        }
    }

    static class XN implements NodeVisitor {
        int x;
        Map<String, String> nses = new HashMap();

        public XN(int x, Map<String, String> nses) {
            this.x = x;
            this.nses = nses;
        }

        @Override
        public void attr(String ns, String name, int resourceId, int type, Object obj) {
            for (int i = 0; i < x; i++) {
                System.out.print("  ");
            }
            if (ns != null) {
                System.out.print(String.format("%s:", nses.get(ns)));
            }
            System.out.print(name);
            if (resourceId != -1) {
                System.out.print(String.format("(%08x)", resourceId));
            }
            if (obj instanceof String) {
                System.out.print(String.format("=[%08x]%s", type, obj));
            } else {
                System.out.print(String.format("=[%08x]%08x", type, obj));
            }
            System.out.println();
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            for (int i = 0; i < x; i++) {
                System.out.print("  ");
            }
            System.out.print("<");
            if (ns != null) {
                System.out.println(nses.get(ns) + ":");
            }
            System.out.println(name);
            return new XN(x + 1, nses);
        }

        @Override
        public void text(String value) {
            // TODO Auto-generated method stub

        }

        @Override
        public void line(int ln) {
            // TODO Auto-generated method stub

        }

        @Override
        public void end() {
            // TODO Auto-generated method stub

        }
    }
}
