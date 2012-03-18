package p.axml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public class Axml {

    public static final int CHUNK_AXML_FILE = 0x00080003;
    public static final int CHUNK_RESOURCEIDS = 0x00080180;
    public static final int CHUNK_STRINGS = 0x001C0001;
    public static final int CHUNK_XML_END_NAMESPACE = 0x00100101;
    public static final int CHUNK_XML_END_TAG = 0x00100103;
    public static final int CHUNK_XML_START_NAMESPACE = 0x00100100;
    public static final int CHUNK_XML_START_TAG = 0x00100102;

    public static final int TYPE_FIRST_INT = 0x10;
    public static final int TYPE_INT_BOOLEAN = 0x12;
    public static final int TYPE_INT_HEX = 0x11;
    public static final int TYPE_REFERENCE = 0x01;
    public static final int TYPE_STRING = 0x03;

    public static List<Item> read(DataIn in) throws IOException {
        List<Item> items = new ArrayList<Item>();
        Ctx ctx = new Ctx();
        int fileSize;
        {
            int type = in.readIntx();
            if (type != CHUNK_AXML_FILE) {
                throw new RuntimeException();
            }
            fileSize = in.readIntx();
        }
        for (int p = in.getCurrentPosition(); p < fileSize; p = in.getCurrentPosition()) {
            int type = in.readIntx();
            int size = in.readIntx();
            switch (type) {
            case CHUNK_XML_START_TAG: {
                Item item = new XmlStartTag();
                item.read(in, ctx);
                items.add(item);
            }
                break;
            case CHUNK_XML_END_TAG: {
                Item item = new XmlEndTag();
                item.read(in, ctx);
                items.add(item);
            }
                break;
            case CHUNK_XML_START_NAMESPACE: {
                Item item = new XmlStartNamespace();
                item.read(in, ctx);
                items.add(item);
            }
                break;
            case CHUNK_XML_END_NAMESPACE: {
                Item item = new XmlEndNamespace();
                item.read(in, ctx);
                items.add(item);
            }
                break;
            case CHUNK_STRINGS:
                ctx.stringItems.read(in, size);
                break;
            case CHUNK_RESOURCEIDS:
                ctx.resourceIds.read(in, size);
                break;
            default:
                throw new RuntimeException();
            }
            in.move(p + size);
        }
        return items;
    }

    public static void write(List<Item> items, DataOut out) throws IOException {
        int size = 8;
        Ctx ctx = new Ctx();
        // prepare start
        for (Item action : items) {
            action.prepare(ctx);
            size += action.getSize();
        }
        ctx.prepare();
        // prepare end

        int itemSize = ctx.stringItems.getSize();
        int stringPadding = 0;
        if (itemSize % 4 != 0) {
            stringPadding = 4 - (itemSize % 4);
            itemSize += stringPadding;
        }
        size += itemSize;
        size += ctx.resourceIds.getSize() * 4;

        // start write
        out.writeInt(CHUNK_AXML_FILE);
        out.writeInt(size);

        {
            out.writeInt(CHUNK_STRINGS);
            out.writeInt(itemSize + 8);
            ctx.stringItems.write(out);
            out.writeBytes(new byte[stringPadding]);// padding
        }
        {
            out.writeInt(CHUNK_RESOURCEIDS);
            out.writeInt(ctx.resourceIds.getSize() + 8);
            ctx.resourceIds.write(out);
        }
        for (Item item : items) {
            out.writeInt(item.type);
            out.writeInt(item.getSize() + 8);
            item.write(out);
        }
    }

}
