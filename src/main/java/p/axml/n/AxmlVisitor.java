package p.axml.n;

public abstract class AxmlVisitor {

    public static final int TYPE_FIRST_INT = 0x10;
    public static final int TYPE_INT_BOOLEAN = 0x12;
    public static final int TYPE_INT_HEX = 0x11;
    public static final int TYPE_REFERENCE = 0x01;
    public static final int TYPE_STRING = 0x03;

    protected AxmlVisitor av;

    public AxmlVisitor(AxmlVisitor av) {
        super();
        this.av = av;
    }

    public NodeVisitor first(String ns, String name) {
        if (av != null) {
            return av.first(ns, name);
        }
        return null;
    };

    public void ns(String prefix, String uri, int ln) {
        if (av != null) {
            av.ns(prefix, uri, ln);
        }
    };

    public void end() {
        if (av != null) {
            av.end();
        }
    }

    public static abstract class NodeVisitor {
        protected NodeVisitor nv;

        public NodeVisitor(NodeVisitor nv) {
            super();
            this.nv = nv;
        }

        public void attr(String ns, String name, int resourceId, int type, Object obj) {
            if (nv != null) {
                nv.attr(ns, name, resourceId, type, obj);
            }
        }

        public NodeVisitor child(String ns, String name) {
            if (nv != null) {
                return nv.child(ns, name);
            }
            return null;
        }

        public void text(String value) {
            if (nv != null) {
                nv.text(value);
            }
        }

        public void line(int ln) {
            if (nv != null) {
                nv.line(ln);
            }
        }

        public void end() {
            if (nv != null) {
                nv.end();
            }
        }
    }

}
