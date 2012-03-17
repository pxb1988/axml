package p.axml;

import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public abstract class AbstractXmlTag extends Item {
    public StringItem name;
    public StringItem namespace;

    public AbstractXmlTag(int type) {
        super(type);
    }

    @Override
    public int getSize() {
        return super.getSize() + 2 * 4;
    }

    @Override
    public void prepare(Ctx ctx) {
        if (this.name != null) {
            this.name = ctx.update(this.name);
        }
        if (this.namespace != null) {
            this.namespace = ctx.update(this.namespace);
        }
    }

    @Override
    public void read(DataIn in, Ctx ctx) {
        super.read(in, ctx);
        int namespaceUri = in.readIntx();
        int name = in.readIntx();

        this.name = ctx.stringItems.get(name);
        if (namespaceUri >= 0) {
            this.namespace = ctx.stringItems.get(name);
        }
    }

    @Override
    public void write(DataOut out) throws IOException {
        super.write(out);
        if (namespace != null) {
            out.writeInt(this.namespace.index);
        } else {
            out.writeInt(-1);
        }
        out.writeInt(name.index);
    }
}
