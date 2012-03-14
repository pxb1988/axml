package p.axml;

import java.io.DataOutput;
import java.io.IOException;

import com.googlecode.dex2jar.reader.io.DataIn;

public abstract class Action {
    public int lineNumber;
    public final int type;

    public Action(int type) {
        super();
        this.type = type;
    }

    public void read(DataIn in, Ctx ctx) {
        in.skip(4);// skip size;
        lineNumber = in.readIntx();
        in.skip(4);/* 0xFFFFFFFF */
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(getSize());
        out.writeInt(this.lineNumber);
        out.writeInt(0xFFFFFFFF);
    }

    public int getSize() {
        return 3 * 4 + 4;
    }

    public abstract void prepare(Ctx ctx);
}
