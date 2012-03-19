package p.axml;

public class XmlEndTag extends AbstractXmlTag {
    public XmlEndTag() {
        super(Axml.CHUNK_XML_END_TAG);
    }

    public XmlEndTag(StringItem name, StringItem namespace) {
        super(Axml.CHUNK_XML_END_TAG, name, namespace);
    }

    public String toString() {
        if (this.name == null) {
            return "END TAG";
        }

        return "</" + this.name.data + ">";
    }
}
