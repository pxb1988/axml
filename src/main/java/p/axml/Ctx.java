package p.axml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ctx {
    public static final int UTF8_FLAG = 0x00000100;
    public StringItems stringItems = new StringItems();
    List<StringItem> styleItems = new ArrayList();
    public ResourceItems resourceIds = new ResourceItems();

    private List<StringItem> otherString = new ArrayList();
    private List<StringItem> resourceString = new ArrayList();
    private Map<Integer, StringItem> resourceId2Str = new HashMap();

    public StringItem update(StringItem item) {
        int i = this.otherString.indexOf(item);
        if (i < 0) {
            this.otherString.add(item);
            return item;
        } else {
            return this.otherString.get(i);
        }
    }

    public StringItem updateWithResourceId(StringItem name, int resourceId) {
        StringItem item = this.resourceId2Str.get(resourceId);
        if (item != null) {
            return item;
        } else {
            StringItem copy = new StringItem();
            copy.data = name.data;
            resourceIds.add(resourceId);
            resourceString.add(copy);
            return copy;
        }
    }

    public void prepare() throws IOException {
        this.stringItems.addAll(resourceString);
        resourceString = null;
        this.stringItems.addAll(otherString);
        otherString = null;
        this.stringItems.prepare();
    }
}
