package p.axml;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.dex2jar.reader.io.DataIn;

public class Ctx {
    public static final int UTF8_FLAG = 0x00000100;
    List<ResourceItem> resourceItems = new ArrayList();
    public StringItems stringItems = new StringItems();
    List<StringItem> styleItems = new ArrayList();

    public void readResourceItems(DataIn in, int size) throws IOException {
        int count = size / 4 - 2;
        for (int i = 0; i < count; i++) {
            ResourceItem resourceItem = new ResourceItem();
            resourceItem.index = i;
            resourceItem.data = in.readIntx();
            resourceItems.add(resourceItem);
             System.out.println(resourceItem);
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

    public void writeResourceItems(DataOutput out) {
    }
}
