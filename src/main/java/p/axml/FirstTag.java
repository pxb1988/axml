package p.axml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class FirstTag extends Tag {

    public static FirstTag create(String ns, String name) {
        XmlStartTag tag = new XmlStartTag(ns == null ? null : new StringItem(ns), new StringItem(name));
        FirstTag firstTag = new FirstTag();
        firstTag._this = tag;
        return firstTag;
    }

    private FirstTag() {
        super();
    }

    public static FirstTag get(List<Item> items) {
        FirstTag ft = new FirstTag();
        Stack<Tag> tags = new Stack<Tag>();
        Tag current = null;
        for (Item item : items) {
            switch (item.type) {
            case Axml.CHUNK_XML_START_NAMESPACE:
            case Axml.CHUNK_XML_END_NAMESPACE:
                AbstractXmlNamespace xmlNamespace = (AbstractXmlNamespace) item;
                ft.ns(xmlNamespace.prefix.data, xmlNamespace.uri.data);
                break;
            case Axml.CHUNK_XML_START_TAG:
                XmlStartTag startTag = (XmlStartTag) item;
                if (current == null) {
                    current = ft;
                    current._this = startTag;
                    tags.push(current);
                } else {
                    Tag n = new Tag(startTag);
                    current.subTags.add(n);
                    tags.push(current);
                    current = n;
                }
                break;
            case Axml.CHUNK_XML_END_TAG:
                current = tags.pop();
                break;
            default:
                throw new RuntimeException();
            }
        }
        return ft;
    }

    private Map<String, String> nsP = new HashMap<String, String>();
    private Map<String, String> nsU = new HashMap<String, String>();

    public void ns(String prefix, String uri) {
        nsP.put(prefix, uri);
        nsU.put(uri, prefix);
    }

    public List<Item> toItemArray() {
        List<Item> itsA = new ArrayList<Item>();
        Stack<Map.Entry<String, String>> s = new Stack<Entry<String, String>>();
        for (Map.Entry<String, String> e : nsP.entrySet()) {
            itsA.add(new XmlStartNamespace(new StringItem(e.getKey()), new StringItem(e.getValue())));
            s.push(e);
        }

        rR(itsA);

        while (s.size() > 0) {
            Map.Entry<String, String> e = s.pop();
            itsA.add(new XmlEndNamespace(new StringItem(e.getKey()), new StringItem(e.getValue())));
        }
        return itsA;
    }

}
