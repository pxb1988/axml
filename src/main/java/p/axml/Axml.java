package p.axml;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.LeArrayDataIn;

public class Axml {

    public static final int CHUNK_AXML_FILE = 0x00080003;
    public static final int CHUNK_RESOURCEIDS = 0x00080180;
    public static final int CHUNK_STRINGS = 0x001C0001;
    public static final int CHUNK_XML_START_NAMESPACE = 0x00100100;
    public static final int CHUNK_XML_END_NAMESPACE = 0x00100101;
    public static final int CHUNK_XML_START_TAG = 0x00100102;
    public static final int CHUNK_XML_END_TAG = 0x00100103;

    public static final int TYPE_STRING = 0x03;
    public static final int TYPE_REFERENCE = 0x01;
    public static final int TYPE_FIRST_INT = 0x10;
    public static final int TYPE_INT_HEX = 0x11;
    public static final int TYPE_INT_BOOLEAN = 0x12;

    public static List<Action> read(DataIn in) throws IOException {
        List<Action> actions = new ArrayList<Action>();
        Ctx ctx = new Ctx();
        int fileSize;
        {
            int type = in.readIntx();
            if (type != CHUNK_AXML_FILE) {
                throw new RuntimeException();
            }
            ctx.fileSize = fileSize = in.readIntx();
        }
        while (in.getCurrentPosition() < fileSize) {
            int type = in.readIntx();
            switch (type) {
            case CHUNK_XML_START_TAG: {
                Action action = new XmlStartTag();
                action.read(in, ctx);
                actions.add(action);
            }
                break;
            case CHUNK_XML_END_TAG: {
                Action action = new XmlEndTag();
                action.read(in, ctx);
                actions.add(action);
            }
                break;
            case CHUNK_XML_START_NAMESPACE: {
                Action action = new XmlStartNamespace();
                action.read(in, ctx);
                actions.add(action);
            }
                break;
            case CHUNK_XML_END_NAMESPACE: {
                Action action = new XmlEndNamespace();
                action.read(in, ctx);
                actions.add(action);
            }
                break;
            case CHUNK_STRINGS:
                ctx.readStringItems(in);
                break;
            case CHUNK_RESOURCEIDS:
                ctx.readResourceItems(in);
                break;
            default:
                throw new RuntimeException();

            }
        }
        return actions;
    }

    public static void write(List<Action> actions, DataOutput out) throws IOException {

        Ctx ctx = new Ctx();
        // prepare start
        for (Action action : actions) {
            action.prepare(ctx);
        }
        ctx.prepare();
        // prepare end

        // start write
        out.writeInt(CHUNK_AXML_FILE);
        out.writeInt(ctx.fileSize);
        ctx.writeStringItems(out);
        ctx.writeResourceItems(out);
        for (Action action : actions) {
            out.writeInt(action.type);
            action.write(out);
        }
    }

    public static void main(String... args) throws Exception {
        InputStream is = new FileInputStream("src/main/resources/AndroidManifest.xml");
        byte[] xml = new byte[is.available()];
        is.read(xml);
        DataIn in = new LeArrayDataIn(xml);
        DataOutputStream os = new DataOutputStream(new FileOutputStream("a"));
        Axml.write(Axml.read(in), os);
        os.close();
    }
}
