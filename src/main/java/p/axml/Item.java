package p.axml;

import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public abstract class Item {
    public int lineNumber;
    public final int type;

    public Item(int type) {
        super();
        this.type = type;
    }

    public int getSize() {
        return 2 * 4;
    }

    public abstract void prepare(Ctx ctx);

    public void read(DataIn in, Ctx ctx) {
        lineNumber = in.readIntx();
        in.skip(4);/* 0xFFFFFFFF */
    }

    public void write(DataOut out) throws IOException {
        out.writeInt(this.lineNumber);
        out.writeInt(0xFFFFFFFF);
    }
}
