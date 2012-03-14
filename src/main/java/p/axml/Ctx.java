package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.googlecode.dex2jar.reader.io.DataIn;

public class Ctx {
    private static final int UTF8_FLAG = 0x00000100;
    List<ResourceItem> resourceItems = new ArrayList();
    List<StringItem> stringItems = new ArrayList();
    List<StringItem> styleItems = new ArrayList();
    Map<Integer, String> stringMap = new TreeMap();
    public int fileSize;
    public int resouceTrunkSize;
    int stringTrunkSize;

    public void readStringItems(DataIn in) throws IOException {
        int trunkOffset = in.getCurrentPosition() - 4;
        stringTrunkSize = in.readIntx();
        int stringCount = in.readIntx();
        int styleOffsetCount = in.readIntx();
        int flags = in.readIntx();
        int stringDataOffset = in.readIntx();
        int stylesOffset = in.readIntx();
        for (int i = 0; i < stringCount; i++) {
            StringItem stringItem = new StringItem();
            stringItem.index = i;
            stringItem.dataOffset = in.readIntx();
            stringItems.add(stringItem);
        }
        if (styleOffsetCount != 0) {
            for (int i = 0; i < styleOffsetCount; i++) {
                StringItem stringItem = new StringItem();
                stringItem.index = i;
                stringItems.add(stringItem);
            }
        }
        if (stringDataOffset + trunkOffset != in.getCurrentPosition()) {
            throw new RuntimeException();
        }

        int endOfStringData = stylesOffset == 0 ? stringTrunkSize : stylesOffset;
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
            if (stylesOffset + trunkOffset != in.getCurrentPosition()) {
                throw new RuntimeException();
            }
            // TODO not support
            throw new RuntimeException();
        }
        if (stringTrunkSize + trunkOffset != in.getCurrentPosition()) {
            throw new RuntimeException();
        }

        for (StringItem item : stringItems) {
            item.data = stringMap.get(item.dataOffset);
            // System.out.println(item);
        }
    }

    public StringItem update(StringItem item) {
        int i = this.stringItems.indexOf(item);
        if (i < 0) {
            this.stringItems.add(item);
            return item;
        } else {
            return this.stringItems.get(i);
        }
    }

    public void readResourceItems(DataIn in) throws IOException {
        resouceTrunkSize = in.readIntx();
        if (resouceTrunkSize < 8 || (resouceTrunkSize % 4) != 0) {
            throw new IOException("Invalid resource ids size (" + resouceTrunkSize + ").");
        }
        int size = resouceTrunkSize / 4 - 2;
        for (int i = 0; i < size; i++) {
            ResourceItem resourceItem = new ResourceItem();
            resourceItem.index = i;
            resourceItem.data = in.readIntx();
            resourceItems.add(resourceItem);
            // System.out.println(resourceItem);
        }
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public void prepare() throws IOException {
        int i = 0;
        int offset = 0;
        baos.reset();
        for (StringItem item : stringItems) {
            item.index = i++;
            item.dataOffset = offset;
            int length = item.data.length();
            byte[] data = item.data.getBytes("UTF-16LE");
            baos.write(length & 0xFF);
            baos.write((length >> 8) & 0xFF);
            baos.write(data);
            baos.write(0);
            baos.write(0);
            offset += 4 + data.length;
        }
        this.stringTrunkSize = 7 * 4 + stringItems.size() * 4 + offset + 0;// TODO
    }

    public void writeStringItems(DataOutput out) throws IOException {
        out.writeInt(stringTrunkSize);
        out.writeInt(this.stringItems.size());
        out.writeInt(0);// TODO
        out.writeInt(0);
        out.writeInt(5 * 4);
        out.writeInt(0);
        for (StringItem item : stringItems) {
            out.writeInt(item.dataOffset);
        }
        out.write(baos.toByteArray());
        // TODO
    }

    public void writeResourceItems(DataOutput out) {
    }
}
