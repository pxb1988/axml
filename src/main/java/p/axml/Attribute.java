package p.axml;

import static p.axml.Axml.TYPE_FIRST_INT;
import static p.axml.Axml.TYPE_INT_BOOLEAN;
import static p.axml.Axml.TYPE_INT_HEX;
import static p.axml.Axml.TYPE_REFERENCE;
import static p.axml.Axml.TYPE_STRING;

import java.io.DataOutput;
import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;

public class Attribute {

    public StringItem namespace, name, valueString;
    int valueType, value;

    public void write(DataOutput out) throws IOException {
        out.write(this.namespace == null ? -1 : this.namespace.index);
        out.write(this.name.index);
        out.write(this.valueString == null ? -1 : this.valueString.index);
        out.write((this.valueType << 24) | 0x000008);
        out.write(this.value);
    }

    public int getSize() {
        return 5 * 4;
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
        this.value = aValue;
        this.valueType = aValueType >>> 24;
//        switch (aValueType >>> 24) {
//        case TYPE_REFERENCE:
//            System.out.println(String.format(" %s = 'REF|0x%08x' //%s", ctx.stringItems.get(aName).data, aValue,
//                    aNS < 0 ? "-1" : ctx.stringItems.get(aNS)));
//            break;
//        case TYPE_STRING:
//            System.out.println(String.format(" %s = 'ST|%s' //%s", ctx.stringItems.get(aName).data,
//                    ctx.stringItems.get(aValueString).data, aNS < 0 ? "-1" : ctx.stringItems.get(aNS)));
//            break;
//        case TYPE_FIRST_INT:
//            System.out.println(String.format(" %s = 'INTF|%d' //%s", ctx.stringItems.get(aName).data, aValue,
//                    aNS < 0 ? "-1" : ctx.stringItems.get(aNS)));
//            break;
//        case TYPE_INT_BOOLEAN:
//            System.out.println(String.format(" %s = 'Z|%s' //%s", ctx.stringItems.get(aName).data, aValue != 0 ? "true"
//                    : "false", aNS < 0 ? "-1" : ctx.stringItems.get(aNS)));
//            break;
//        case TYPE_INT_HEX:
//            System.out.println(String.format(" %s = 'INTH|%08x' //%s", ctx.stringItems.get(aName).data, aValue,
//                    aNS < 0 ? "-1" : ctx.stringItems.get(aNS)));
//            break;
//        default:
//            throw new RuntimeException();
//        }
    }

    public void prepare(Ctx ctx) {
        if (this.namespace != null) {
            this.namespace = ctx.update(this.namespace);
        }
        if (this.name != null) {
            this.name = ctx.update(this.name);
        }
        if (this.valueString != null) {
            this.valueString = ctx.update(this.valueString);
        }
    }

}
