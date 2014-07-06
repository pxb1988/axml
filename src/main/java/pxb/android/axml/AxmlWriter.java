/*
 * Copyright (c) 2009-2013 Panxiaobo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pxb.android.axml;

import static pxb.android.axml.AxmlParser.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

import pxb.android.StringBlock;
import pxb.android.StringItem;

/**
 * a class to write android axml
 * 
 * @author <a href="mailto:pxb1988@gmail.com">Panxiaobo</a>
 */
public class AxmlWriter extends AxmlVisitor {
    static final Comparator<Attr> ATTR_CMP = new Comparator<Attr>() {

        @Override
        public int compare(Attr a, Attr b) {
            int x = a.name.resourceId - b.name.resourceId;
            if (x == 0) {
                x = a.name.data.compareTo(b.name.data);
                if (x == 0) {
                    boolean aNsIsnull = a.ns == null;
                    boolean bNsIsnull = b.ns == null;
                    if (aNsIsnull) {
                        if (bNsIsnull) {
                            x = 0;
                        } else {
                            x = -1;
                        }
                    } else {
                        if (bNsIsnull) {
                            x = 1;
                        } else {
                            x = a.ns.data.compareTo(b.ns.data);
                        }
                    }

                }
            }
            return x;
        }
    };

    static class Attr {

        public int index;
        public StringItem name;
        public StringItem ns;
        public int type;
        public Object value;
        public StringItem raw;

        public Attr(StringItem ns, StringItem name) {
            super();
            this.ns = ns;
            this.name = name;
        }
    }

    class NodeImpl extends NodeVisitor {
        private Set<Attr> attrs = new TreeSet<Attr>(ATTR_CMP);
        private List<NodeImpl> children = new ArrayList<NodeImpl>();
        private int line;
        private StringItem name;
        private StringItem ns;
        private StringItem text;
        private int textLineNumber;
        Attr id;
        Attr style;
        Attr clz;

        public NodeImpl(StringItem ns, StringItem name) {
            super(null);
            this.ns = ns;
            this.name = name;
        }

        @Override
        public void attr(String ns, String name, int resourceId, int type, Object value) {
            if (name == null) {
                throw new RuntimeException("name can't be null");
            }

            Attr a = new Attr(addNs(ns), stringItems.add(name, resourceId));
            a.type = type;

            if (value instanceof ValueWrapper) {
                ValueWrapper valueWrapper = (ValueWrapper) value;
                if (valueWrapper.raw != null) {
                    a.raw = stringItems.add(valueWrapper.raw);
                }
                a.value = valueWrapper.ref;
                switch (valueWrapper.type) {
                case ValueWrapper.CLASS:
                    clz = a;
                    break;
                case ValueWrapper.ID:
                    id = a;
                    break;
                case ValueWrapper.STYLE:
                    style = a;
                    break;
                }
            } else if (type == TYPE_STRING) {
                StringItem raw = stringItems.add((String) value);
                a.raw = raw;
                a.value = raw;

            } else {
                a.raw = null;
                a.value = value;
            }

            attrs.add(a);
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            if (name == null) {
                throw new RuntimeException("name can't be null");
            }
            NodeImpl child = new NodeImpl(addNs(ns), stringItems.add(name));
            this.children.add(child);
            return child;
        }

        @Override
        public void end() {
        }

        @Override
        public void line(int ln) {
            this.line = ln;
        }

        public int prepare(AxmlWriter axmlWriter) {
            int attrIndex = 0;
            for (Attr attr : attrs) {
                attr.index = attrIndex++;
            }

            int size = 24 + 36 + attrs.size() * 20;// 24 for end tag,36+x*20 for
            // start tag
            for (NodeImpl child : children) {
                size += child.prepare(axmlWriter);
            }
            if (text != null) {
                size += 28;
            }
            return size;
        }

        @Override
        public void text(int ln, String value) {
            this.text = stringItems.add(value);
            this.textLineNumber = ln;
        }

        void write(ByteBuffer out) throws IOException {
            // start tag
            out.putInt(RES_XML_START_ELEMENT_TYPE | (0x0010 << 16));
            out.putInt(36 + attrs.size() * 20);
            out.putInt(line);
            out.putInt(0xFFFFFFFF);
            out.putInt(ns != null ? this.ns.index : -1);
            out.putInt(name.index);
            out.putInt(0x00140014);// TODO
            out.putShort((short) this.attrs.size());
            out.putShort((short) (id == null ? 0 : id.index + 1));
            out.putShort((short) (clz == null ? 0 : clz.index + 1));
            out.putShort((short) (style == null ? 0 : style.index + 1));
            for (Attr attr : attrs) {
                out.putInt(attr.ns == null ? -1 : attr.ns.index);
                out.putInt(attr.name.index);
                out.putInt(attr.raw != null ? attr.raw.index : -1);
                out.putInt((attr.type << 24) | 0x000008);
                Object v = attr.value;
                if (v instanceof StringItem) {
                    out.putInt(((StringItem) attr.value).index);
                } else if (v instanceof Boolean) {
                    out.putInt(Boolean.TRUE.equals(v) ? -1 : 0);
                } else {
                    out.putInt((Integer) attr.value);
                }
            }

            if (this.text != null) {
                out.putInt(RES_XML_CDATA_TYPE | (0x0010 << 16));
                out.putInt(28);
                out.putInt(textLineNumber);
                out.putInt(0xFFFFFFFF);
                out.putInt(text.index);
                out.putInt(0x00000008);
                out.putInt(0x00000000);
            }

            // children
            for (NodeImpl child : children) {
                child.write(out);
            }

            // end tag
            out.putInt(RES_XML_END_ELEMENT_TYPE | (0x0010 << 16));
            out.putInt(24);
            out.putInt(-1);
            out.putInt(0xFFFFFFFF);
            out.putInt(ns != null ? this.ns.index : -1);
            out.putInt(name.index);
        }
    }

    static class Ns {
        int ln;
        StringItem prefix;
        StringItem uri;

        public Ns(StringItem prefix, StringItem uri, int ln) {
            super();
            this.prefix = prefix;
            this.uri = uri;
            this.ln = ln;
        }
    }

    private List<NodeImpl> firsts = new ArrayList<NodeImpl>(3);

    private Map<String, Ns> nses = new HashMap<String, Ns>();

    private StringBlock stringItems = new StringBlock();

    @Override
    public NodeVisitor child(String ns, String name) {
        if (name == null) {
            throw new RuntimeException("name can't be null");
        }
        NodeImpl first = new NodeImpl(addNs(ns), stringItems.add(name));
        this.firsts.add(first);
        return first;
    }

    @Override
    public void end() {
    }

    @Override
    public void ns(String prefix, String uri, int ln) {
        Ns ns = nses.get(uri);
        StringItem prefixItem = prefix == null ? null : stringItems.add(prefix);
        if (ns == null) {
            nses.put(uri, new Ns(prefixItem, stringItems.add(uri), ln));
        } else {
            ns.prefix = prefixItem;
            ns.ln = ln;
        }
    }

    private int prepare() throws IOException {
        int size = 0;

        for (NodeImpl first : firsts) {
            size += first.prepare(this);
        }
        {
            int a = 0;
            for (Map.Entry<String, Ns> e : nses.entrySet()) {
                Ns ns = e.getValue();
                if (ns.prefix == null) {
                    ns.prefix = stringItems.add(String.format("axml_auto_%02d", a++));
                }
            }
        }

        size += nses.size() * 24 * 2;

        this.stringItems.prepare();
        size += stringItems.getStringPoolSectionSize();
        size += stringItems.getXmlResourceTableSectionSize();
        return size;
    }

    public byte[] toByteArray() throws IOException {

        int size = 8 + prepare();
        ByteBuffer out = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN);

        out.putInt(RES_XML_TYPE | (0x0008 << 16));
        out.putInt(size);

        stringItems.writeStringPoolSection(out);
        stringItems.writeXmlResourceTableSection(out);

        Stack<Ns> stack = new Stack<Ns>();
        for (Map.Entry<String, Ns> e : this.nses.entrySet()) {
            Ns ns = e.getValue();
            stack.push(ns);
            out.putInt(RES_XML_START_NAMESPACE_TYPE | (0x0010 << 16));
            out.putInt(24);
            out.putInt(-1);
            out.putInt(0xFFFFFFFF);
            out.putInt(ns.prefix.index);
            out.putInt(ns.uri.index);
        }

        for (NodeImpl first : firsts) {
            first.write(out);
        }

        while (stack.size() > 0) {
            Ns ns = stack.pop();
            out.putInt(RES_XML_END_NAMESPACE_TYPE | (0x0010 << 16));
            out.putInt(24);
            out.putInt(ns.ln);
            out.putInt(0xFFFFFFFF);
            out.putInt(ns.prefix.index);
            out.putInt(ns.uri.index);
        }
        return out.array();
    }

    private StringItem addNs(String uri) {
        if (uri == null) {
            return null;
        }
        Ns ns = nses.get(uri);
        if (ns == null) {
            StringItem uriItem = stringItems.add(uri);
            nses.put(uri, new Ns(null, uriItem, 0));
            return uriItem;
        } else {
            return ns.uri;
        }
    }
}
