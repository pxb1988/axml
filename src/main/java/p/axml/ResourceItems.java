package p.axml;

import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public class ResourceItems extends ArrayList<Integer> {
    public void read(DataIn in, int size) throws IOException {
        int count = size / 4 - 2;
        for (int i = 0; i < count; i++) {
            this.add(in.readIntx());
        }
    }

    public int getSize() {
        return this.size() * 4;
    }

    public void write(DataOut out) throws IOException {
        for (Integer i : this) {
            out.writeInt(i);
        }
    }
}
