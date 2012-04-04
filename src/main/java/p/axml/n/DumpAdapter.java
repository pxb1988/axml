package p.axml.n;

import java.util.HashMap;
import java.util.Map;

public class DumpAdapter extends AxmlVisitor {

    public DumpAdapter(AxmlVisitor av) {
        super(av);
    }

    private Map<String, String> nses = new HashMap<String, String>();

    @Override
    public void ns(String prefix, String uri, int ln) {
        System.out.println(prefix + "=" + uri);
        this.nses.put(uri, prefix);
        super.ns(prefix, uri, ln);
    }

    @Override
    public NodeVisitor first(String ns, String name) {
        System.out.print("<");
        if (ns != null) {
            System.out.println(nses.get(ns) + ":");
        }
        System.out.println(name);
        NodeVisitor nv = super.first(ns, name);
        if (nv != null) {
            XN x = new XN(nv, 1, nses);
            return x;
        }
        return null;
    }

    @Override
    public void end() {
        super.end();
    }

    static class XN extends NodeVisitor {
        int x;
        Map<String, String> nses = new HashMap<String, String>();

        public XN(NodeVisitor nv, int x, Map<String, String> nses) {
            super(nv);
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
                System.out.print(String.format("=[%08x]\"%s\"", type, obj));
            } else {
                System.out.print(String.format("=[%08x]%08x", type, obj));
            }
            System.out.println();
            super.attr(ns, name, resourceId, type, obj);
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
            NodeVisitor nv = super.child(ns, name);
            if (nv != null) {
                return new XN(nv, x + 1, nses);
            }
            return null;
        }
    }

}
