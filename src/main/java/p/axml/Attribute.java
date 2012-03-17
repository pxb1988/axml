package p.axml;

import static p.axml.Axml.TYPE_FIRST_INT;
import static p.axml.Axml.TYPE_INT_BOOLEAN;
import static p.axml.Axml.TYPE_INT_HEX;
import static p.axml.Axml.TYPE_REFERENCE;
import static p.axml.Axml.TYPE_STRING;

import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public class Attribute {

    public StringItem namespace, name, valueString;
    public int valueType, value;
    public int resourceId = 0;

    public int getSize() {
        return 5 * 4;
    }

    public void prepare(Ctx ctx) {
        if (this.namespace != null) {
            this.namespace = ctx.update(this.namespace);
        }
        if (this.name != null) {
            if (resourceId != 0) {
                this.name = ctx.updateWithResourceId(this.name, this.resourceId);
            } else {
                this.name = ctx.update(this.name);
            }
        }
        if (this.valueString != null) {
            this.valueString = ctx.update(this.valueString);
        }
    }

    public void read(DataIn in, Ctx ctx) {
        int aNS = in.readIntx();
        int aName = in.readIntx();
        int aValueString = in.readIntx();
        int aValueType = in.readIntx();
        int aValue = in.readIntx();
        if ((aValueType & 0x00FFFFFF) != 0x000008) {
            throw new RuntimeException();
        }
        if (aNS >= 0) {
            this.namespace = ctx.stringItems.get(aNS);
        }
        this.name = ctx.stringItems.get(aName);
        if (aValueString >= 0) {
            this.valueString = ctx.stringItems.get(aValueString);
        }

        if (aName < ctx.resourceIds.size()) {
            this.resourceId = ctx.resourceIds.get(aName);
        }

        this.value = aValue;
        this.valueType = aValueType >>> 24;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(" ").append(this.name.data).append(" = \"");
        switch (valueType) {
        case TYPE_REFERENCE:
            sb.append(String.format("REF|0x%08x", this.value));
            break;
        case TYPE_STRING:
            sb.append(String.format("ST|%s", this.valueString.data));
            break;
        case TYPE_FIRST_INT:
            sb.append(String.format("INTF|%d", this.value));
            break;
        case TYPE_INT_BOOLEAN:
            sb.append(String.format("Z|%s", this.value != 0 ? "true" : "false"));
            break;
        case TYPE_INT_HEX:
            sb.append(String.format("INTH|%08x", this.value));
            break;
        default:
            sb.append("UNKNOWN");
        }
        sb.append("\" ");
        if (this.resourceId != 0) {
            sb.append("p:rs=").append(String.format("\"0x%08x\" ", this.resourceId));
        }
        if (this.namespace != null) {
            sb.append("p:ns=").append(this.namespace.data);
        }
        return sb.toString();
    }

    public void write(DataOut out) throws IOException {
        out.writeInt(this.namespace == null ? -1 : this.namespace.index);
        out.writeInt(this.name.index);
        out.writeInt(this.valueString == null ? -1 : this.valueString.index);
        out.writeInt((this.valueType << 24) | 0x000008);
        out.writeInt(this.value);
    }

}
