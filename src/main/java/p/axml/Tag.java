package p.axml;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    protected List<Tag> subTags = new ArrayList<Tag>();
    protected XmlStartTag _this;

    public Tag() {
        super();
    }

    public Tag(XmlStartTag startTag) {
        super();
        this._this = startTag;
    }

    public Tag tag(String ns, String name) {
        Tag tag = new Tag();
        tag._this = new XmlStartTag(ns != null ? new StringItem(ns) : null, new StringItem(name));
        subTags.add(tag);
        return tag;
    }

    public Attribute attr(String ns, String name, int resourceId) {
        Attribute currentAttr = new Attribute(ns != null ? new StringItem(ns) : null, new StringItem(name), resourceId);
        _this.attrs.add(currentAttr);
        return currentAttr;
    }

    protected void rR(List<Item> items) {
        items.add(_this);
        for (Tag t : subTags) {
            t.rR(items);
        }
        items.add(new XmlEndTag(_this.name, _this.namespace));
    }
}