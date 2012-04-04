package p.axml.n;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import p.axml.Attribute;
import p.axml.ResourceItems;
import p.axml.StringItem;
import p.axml.StringItems;

import com.googlecode.dex2jar.reader.io.DataOut;
import com.googlecode.dex2jar.reader.io.LeDataOut;

public class AxmlWriter implements AxmlVisitor {
    private NodeImpl first;
    Set<Ns> nses = new HashSet();

    static class Ns {
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
            result = prime * result + ((uri == null) ? 0 : uri.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Ns other = (Ns) obj;
            if (prefix == null) {
                if (other.prefix != null)
                    return false;
            } else if (!prefix.equals(other.prefix))
                return false;
            if (uri == null) {
                if (other.uri != null)
                    return false;
            } else if (!uri.equals(other.uri))
                return false;
            return true;
        }

        public Ns(StringItem prefix, StringItem uri, int ln) {
            super();
            this.prefix = prefix;
            this.uri = uri;
            this.ln = ln;
        }

        StringItem prefix;
        StringItem uri;
        int ln;
    }

    static class Attr {
        public StringItem ns;
        public StringItem name;
        public int resourceId;
        public int type;
        public Object value;

        public Attr(StringItem ns, StringItem name, int resourceId, int type, Object value) {
            super();
            this.ns = ns;
            this.name = name;
            this.resourceId = resourceId;
            this.type = type;
            this.value = value;
        }

        public void prepare(AxmlWriter axmlWriter) {
            ns = axmlWriter.update(ns);
            if (this.name != null) {
                if (resourceId != -1) {
                    this.name = axmlWriter.updateWithResourceId(this.name, this.resourceId);
                } else {
                    this.name = axmlWriter.update(this.name);
                }
            }
            if (value instanceof StringItem) {
                value = axmlWriter.update((StringItem) value);
            }
        }
    }

    static class NodeImpl implements NodeVisitor {
        private StringItem ns;
        private StringItem name;
        private int line;
        private String text;
        private List<NodeImpl> children = new ArrayList();
        private Map<String, Attr> attrs = new HashMap();

        public NodeImpl(String ns, String name) {
            super();
            this.ns = ns == null ? null : new StringItem(ns);
            this.name = name == null ? null : new StringItem(name);
        }

        @Override
        public void attr(String ns, String name, int resourceId, int type, Object value) {
            if (name == null) {
                throw new RuntimeException("name can't be null");
            }
            attrs.put((ns == null ? ".*&&" : ns) + "..." + name, new Attr(ns == null ? null : new StringItem(ns),
                    new StringItem(name), resourceId, type, type == TYPE_STRING ? new StringItem((String) value)
                            : value));
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            NodeImpl child = new NodeImpl(ns, name);
            this.children.add(child);
            return child;
        }

        @Override
        public void text(String value) {
            // TODO impl
            this.text = value;
        }

        @Override
        public void line(int ln) {
            this.line = ln;
        }

        @Override
        public void end() {
        }

        public int prepare(AxmlWriter axmlWriter) {
            ns = axmlWriter.update(ns);
            name = axmlWriter.update(name);
            for (Attr attr : attrs.values()) {
                attr.prepare(axmlWriter);
            }
            int size = 24 + 36 + attrs.size() * 20;// 24 for end tag,36+x*20 for start tag
            for (NodeImpl child : children) {
                size += child.prepare(axmlWriter);
            }
            return size;
        }

        void write(DataOut out) throws IOException {
            // start tag
            out.writeInt(AxmlReader.CHUNK_XML_START_TAG);
            out.writeInt(36 + attrs.size() * 20);
            out.writeInt(line);
            out.writeInt(0xFFFFFFFF);
            out.writeInt(ns != null ? this.ns.index : -1);
            out.writeInt(name.index);
            out.writeInt(0x00140014);// TODO
            out.writeShort(this.attrs.size());
            out.writeShort(0);
            out.writeShort(0);
            out.writeShort(0);
            for (Attr attr : attrs.values()) {
                out.writeInt(attr.ns == null ? -1 : attr.ns.index);
                out.writeInt(attr.name.index);
                out.writeInt(attr.value instanceof StringItem ? ((StringItem) attr.value).index : -1);
                out.writeInt((attr.type << 24) | 0x000008);
                if (attr.value instanceof StringItem) {
                    out.writeInt(((StringItem) attr.value).index);
                } else {
                    out.writeInt((Integer) attr.value);

                }
            }

            // children
            for (NodeImpl child : children) {
                child.write(out);
            }
            // TODO text

            // end tag
            out.writeInt(AxmlReader.CHUNK_XML_END_TAG);
            out.writeInt(24);
            out.writeInt(-1);
            out.writeInt(0xFFFFFFFF);
            out.writeInt(ns != null ? this.ns.index : -1);
            out.writeInt(name.index);
        }
    }

    @Override
    public NodeVisitor first(String ns, String name) {
        if (first != null) {
            throw new RuntimeException();
        }
        this.first = new NodeImpl(ns, name);
        return this.first;
    }

    @Override
    public void ns(String prefix, String uri, int ln) {
        nses.add(new Ns(new StringItem(uri), new StringItem(prefix), ln));
    }

    @Override
    public void end() {
    }

    public ResourceItems resourceIds = new ResourceItems();

    private List<StringItem> otherString = new ArrayList();
    private List<StringItem> resourceString = new ArrayList();
    private Map<Integer, StringItem> resourceId2Str = new HashMap();

    public StringItems stringItems = new StringItems();
    List<StringItem> styleItems = new ArrayList();

    public StringItem update(StringItem item) {
        int i = this.otherString.indexOf(item);
        if (i < 0) {
            StringItem copy = new StringItem(item.data);
            this.otherString.add(copy);
            return copy;
        } else {
            return this.otherString.get(i);
        }
    }

    public StringItem updateWithResourceId(StringItem name, int resourceId) {
        StringItem item = this.resourceId2Str.get(resourceId);
        if (item != null) {
            return item;
        } else {
            StringItem copy = new StringItem(name.data);
            resourceIds.add(resourceId);
            resourceString.add(copy);
            resourceId2Str.put(resourceId, copy);
            return copy;
        }
    }

    private int prepare() throws IOException {
        for (Ns ns : nses) {
            ns.prefix = update(ns.prefix);
            ns.uri = update(ns.uri);
        }

        int size = nses.size() * 24 * 2;
        size += first.prepare(this);

        this.stringItems.addAll(resourceString);
        resourceString = null;
        this.stringItems.addAll(otherString);
        otherString = null;
        this.stringItems.prepare();
        int stringSize = this.stringItems.getSize();
        if (stringSize % 4 != 0) {
            stringSize += stringSize - stringSize % 4;
        }
        size += 8 + stringSize;
        size += 8 + resourceIds.getSize();
        return size;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        DataOut out = new LeDataOut(os);
        int size = prepare();
        out.writeInt(AxmlReader.CHUNK_AXML_FILE);
        out.writeInt(size + 8);

        int stringSize = this.stringItems.getSize();
        int padding = 0;
        if (stringSize % 4 != 0) {
            padding = stringSize - stringSize % 4;
        }
        out.writeInt(AxmlReader.CHUNK_STRINGS);
        out.writeInt(stringSize + padding + 8);
        out.writeBytes(new byte[padding]);

        out.writeInt(AxmlReader.CHUNK_RESOURCEIDS);
        out.writeInt(8 + this.resourceIds.getSize());

        Stack<Ns> stack = new Stack();
        for (Ns ns : this.nses) {
            stack.push(ns);
            out.writeInt(AxmlReader.CHUNK_XML_START_NAMESPACE);
            out.writeInt(24);
            out.writeInt(-1);
            out.writeInt(0xFFFFFFFF);
            out.writeInt(ns.prefix.index);
            out.writeInt(ns.uri.index);
        }

        first.write(out);

        while (stack.size() > 0) {
            Ns ns = stack.pop();
            out.writeInt(AxmlReader.CHUNK_XML_END_NAMESPACE);
            out.writeInt(24);
            out.writeInt(ns.ln);
            out.writeInt(0xFFFFFFFF);
            out.writeInt(ns.prefix.index);
            out.writeInt(ns.uri.index);
        }
        return os.toByteArray();
    }
}
