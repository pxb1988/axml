package p.axml.n;

public interface AxmlVisitor {

    int TYPE_FIRST_INT = 0x10;
    int TYPE_INT_BOOLEAN = 0x12;
    int TYPE_INT_HEX = 0x11;
    int TYPE_REFERENCE = 0x01;
    int TYPE_STRING = 0x03;

    NodeVisitor first(String ns, String name);

    void ns(String prefix, String uri, int ln);

    void end();

    public interface NodeVisitor {
        void attr(String ns, String name, int resourceId, int type, Object obj);

        NodeVisitor child(String ns, String name);

        void text(String value);

        void line(int ln);

        void end();
    }

}
