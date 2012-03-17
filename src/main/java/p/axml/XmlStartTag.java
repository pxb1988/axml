package p.axml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public class XmlStartTag extends AbstractXmlTag {

    public List<Attribute> attrs = new ArrayList<Attribute>();
    public int classAttribute;
    public int idAttribute;
    public int styleAttribute;

    public XmlStartTag() {
        super(Axml.CHUNK_XML_START_TAG);
    }

    @Override
    public int getSize() {
        return super.getSize() + 4 + 4 * 2 + this.attrs.size() * 5 * 4;
    }

    @Override
    public void prepare(Ctx ctx) {
        super.prepare(ctx);
        for (Attribute attr : attrs) {
            attr.prepare(ctx);
        }
    }

    @Override
    public void read(DataIn in, Ctx ctx) {
        super.read(in, ctx);
        int flag = in.readIntx();
        int attributeCount = in.readUShortx();
        idAttribute = in.readUShortx() - 1;
        classAttribute = in.readUShortx() - 1;
        styleAttribute = in.readUShortx() - 1;
        for (int i = 0; i < attributeCount; i++) {
            Attribute a = new Attribute();
            a.read(in, ctx);
            attrs.add(a);
        }
    }

    public String toString() {
        if (this.name == null) {
            return "START TAG";
        }
        StringBuilder sb = new StringBuilder().append("<").append(this.name.data);
        if (this.namespace != null) {
            sb.append(" p:ns=\"").append(this.namespace.data).append("\"");
        }
        if (this.attrs.size() > 0) {
            sb.append("\n");
            for (Attribute attr : attrs) {
                sb.append(attr).append("\n");
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override
    public void write(DataOut out) throws IOException {
        super.write(out);
        out.writeInt(0);// TODO
        out.writeShort(this.attrs.size());
        out.writeShort(this.idAttribute + 1);
        out.writeShort(this.classAttribute + 1);
        out.writeShort(this.styleAttribute + 1);
        for (Attribute attr : attrs) {
            attr.write(out);
        }
    }

}
