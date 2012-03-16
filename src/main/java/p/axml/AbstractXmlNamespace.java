package p.axml;

import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public abstract class AbstractXmlNamespace extends Item {
    public StringItem prefix;
    public StringItem uri;

    public AbstractXmlNamespace(int type) {
        super(type);
    }

    @Override
    public int getSize() {
        return super.getSize() + 2 * 4;
    }

    @Override
    public void prepare(Ctx ctx) {
        if (this.prefix != null) {
            this.prefix = ctx.update(prefix);
        }
        if (this.uri != null) {
            this.uri = ctx.update(uri);
        }
    }

    @Override
    public void read(DataIn in, Ctx ctx) {
        super.read(in, ctx);
        int prefix = in.readIntx();
        int uri = in.readIntx();
        this.prefix = ctx.stringItems.get(prefix);
        this.uri = ctx.stringItems.get(uri);
    }

    @Override
    public void write(DataOut out) throws IOException {
        super.write(out);
        out.writeInt(prefix.index);
        out.writeInt(uri.index);
    }

}
