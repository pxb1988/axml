package p.axml.n;

import static p.axml.n.AxmlVisitor.TYPE_STRING;

import java.io.IOException;
import java.util.Stack;

import p.axml.ResourceItems;
import p.axml.StringItems;
import p.axml.n.AxmlVisitor.NodeVisitor;

import com.googlecode.dex2jar.reader.io.DataIn;

public class AxmlReader {
    static final int CHUNK_AXML_FILE = 0x00080003;
    static final int CHUNK_RESOURCEIDS = 0x00080180;
    static final int CHUNK_STRINGS = 0x001C0001;
    static final int CHUNK_XML_END_NAMESPACE = 0x00100101;
    static final int CHUNK_XML_END_TAG = 0x00100103;
    static final int CHUNK_XML_START_NAMESPACE = 0x00100100;
    static final int CHUNK_XML_START_TAG = 0x00100102;
    private StringItems stringItems = new StringItems();
    private ResourceItems resourceIds = new ResourceItems();
    private DataIn in;

    public AxmlReader(DataIn in) {
        super();
        this.in = in;
    }

    public void accept(AxmlVisitor documentVisitor) throws IOException {
        DataIn in = this.in;
        int fileSize;
        {
            int type = in.readIntx();
            if (type != CHUNK_AXML_FILE) {
                throw new RuntimeException();
            }
            fileSize = in.readIntx();
        }
        NodeVisitor nv = null;
        Stack<NodeVisitor> nvs = new Stack<NodeVisitor>();
        String name, ns;
        int nameIdx, nsIdx;
        int lineNumber;
        for (int p = in.getCurrentPosition(); p < fileSize; p = in.getCurrentPosition()) {
            int type = in.readIntx();
            int size = in.readIntx();
            switch (type) {
            case CHUNK_XML_START_TAG: {
                {
                    lineNumber = in.readIntx();
                    in.skip(4);/* 0xFFFFFFFF */
                    nsIdx = in.readIntx();
                    nameIdx = in.readIntx();
                    int flag = in.readIntx();// 0x00140014 ?
                    if (flag != 0x00140014) {
                        throw new RuntimeException();
                    }
                    name = stringItems.get(nameIdx).data;
                    ns = nsIdx >= 0 ? stringItems.get(nsIdx).data : null;

                    if (nv == null) {
                        nv = documentVisitor.first(ns, name);
                    } else {
                        nvs.push(nv);
                        nv = nv.child(ns, name);
                    }

                    nv.line(lineNumber);
                }

                int attributeCount = in.readUShortx();
                // int idAttribute = in.readUShortx();
                // int classAttribute = in.readUShortx();
                // int styleAttribute = in.readUShortx();
                in.skip(6);

                for (int i = 0; i < attributeCount; i++) {
                    nsIdx = in.readIntx();
                    nameIdx = in.readIntx();
                    in.skip(4);// skip valueString
                    int aValueType = in.readIntx() >>> 24;
                    int aValue = in.readIntx();

                    name = stringItems.get(nameIdx).data;
                    ns = nsIdx >= 0 ? stringItems.get(nsIdx).data : null;
                    Object value = aValueType == TYPE_STRING ? stringItems.get(aValue).data : aValue;
                    int resourceId = nameIdx < resourceIds.size() ? resourceIds.get(nameIdx) : -1;
                    nv.attr(ns, name, resourceId, aValueType, value);
                }
            }
                break;
            case CHUNK_XML_END_TAG: {
                in.skip(size - 8);
                nv.end();
                if (nvs.size() > 0) {
                    nv = nvs.pop();
                } else {
                    nv = null;
                }
            }
                break;
            case CHUNK_XML_START_NAMESPACE:
                lineNumber = in.readIntx();
                in.skip(4);/* 0xFFFFFFFF */
                int prefixIdx = in.readIntx();
                nsIdx = in.readIntx();
                documentVisitor.ns(stringItems.get(prefixIdx).data, stringItems.get(nsIdx).data, lineNumber);
                break;
            case CHUNK_XML_END_NAMESPACE:
                in.skip(size - 8);
                break;
            case CHUNK_STRINGS:
                stringItems.read(in, size);
                break;
            case CHUNK_RESOURCEIDS:
                resourceIds.read(in, size);
                break;
            default:
                throw new RuntimeException();
            }
            in.move(p + size);
        }
    }
}
