package p.axml;

import static p.axml.Ctx.UTF8_FLAG;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.DataOut;

public class StringItems extends ArrayList<StringItem> {

    public void read(DataIn in, int size) throws IOException {
        int trunkOffset = in.getCurrentPosition() - 4;
        int stringCount = in.readIntx();
        int styleOffsetCount = in.readIntx();
        int flags = in.readIntx();
        int stringDataOffset = in.readIntx();
        int stylesOffset = in.readIntx();
        for (int i = 0; i < stringCount; i++) {
            StringItem stringItem = new StringItem();
            stringItem.index = i;
            stringItem.dataOffset = in.readIntx();
            this.add(stringItem);
        }
        Map<Integer, String> stringMap = new TreeMap();
        if (styleOffsetCount != 0) {
            throw new RuntimeException();
            // for (int i = 0; i < styleOffsetCount; i++) {
            // StringItem stringItem = new StringItem();
            // stringItem.index = i;
            // stringItems.add(stringItem);
            // }
        }
        int endOfStringData = stylesOffset == 0 ? size : stylesOffset;
        int base = in.getCurrentPosition();
        if (0 != (flags & UTF8_FLAG)) {
            for (int p = base; p < endOfStringData; p = in.getCurrentPosition()) {
                int length = (int) in.readLeb128();
                ByteArrayOutputStream bos = new ByteArrayOutputStream(length + 10);
                for (int r = in.readByte(); r != 0; r = in.readByte()) {
                    bos.write(r);
                }
                String value = new String(bos.toByteArray(), "UTF-8");
                stringMap.put(p - base, value);
            }
        } else {
            for (int p = base; p < endOfStringData; p = in.getCurrentPosition()) {
                int length = in.readShortx();
                byte[] data = in.readBytes(length * 2);
                in.skip(2);
                String value = new String(data, "UTF-16LE");
                stringMap.put(p - base, value);
            }
        }
        if (stylesOffset != 0) {
            throw new RuntimeException();
        }
        for (StringItem item : this) {
            item.data = stringMap.get(item.dataOffset);
            // System.out.println(item);
        }
    }

    byte[] stringData;

    public void prepare() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = 0;
        int offset = 0;
        baos.reset();
        for (StringItem item : this) {
            item.index = i++;
            item.dataOffset = offset;
            int length = item.data.length();
            byte[] data = item.data.getBytes("UTF-16LE");
            baos.write(length);
            baos.write(length >> 8);
            baos.write(data);
            baos.write(0);
            baos.write(0);
            offset += 4 + data.length;
        }
        // TODO
        stringData = baos.toByteArray();
    }

    public int getSize() {
        return 5 * 4 + this.size() * 4 + stringData.length + 0;// TODO
    }

    public void write(DataOut out) throws IOException {
        out.writeInt(this.size());
        out.writeInt(0);// TODO
        out.writeInt(0);
        out.writeInt(7 * 4 + this.size() * 4);
        out.writeInt(0);
        for (StringItem item : this) {
            out.writeInt(item.dataOffset);
        }
        out.writeBytes(stringData);
        // TODO
    }
}
