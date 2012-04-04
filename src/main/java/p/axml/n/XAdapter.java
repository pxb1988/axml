package p.axml.n;

public class XAdapter extends AxmlVisitor {

    public XAdapter() {
        super(null);
    }

    @Override
    public NodeVisitor first(String ns, String name) {
        return new XNode();
    }

    static class XNode extends NodeVisitor {

        public XNode() {
            super(null);
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            return new XNode();
        }
    }

}
