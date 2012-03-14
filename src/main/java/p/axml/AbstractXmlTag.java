package p.axml;

import java.io.DataOutput;
import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;

public abstract class AbstractXmlTag extends Action {
    public AbstractXmlTag(int type) {
        super(type);
    }

    public StringItem namespace;
    public StringItem name;

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
    public void write(DataOutput out) throws IOException {
        super.write(out);
        out.writeInt(name.index);
        if (namespace != null) {
            out.writeInt(this.namespace.index);
        } else {
            out.writeInt(0);
        }
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
}
