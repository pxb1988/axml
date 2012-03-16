package p.axml;

public class XmlStartNamespace extends AbstractXmlNamespace {

    public XmlStartNamespace() {
        super(Axml.CHUNK_XML_START_NAMESPACE);
    }

    public String toString() {
        if (this.prefix == null || this.uri == null) {
            return "START NS";
        }
        return "START xmlns:" + this.prefix.data +"="+ this.uri.data;
    }
}
