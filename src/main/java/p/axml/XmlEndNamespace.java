package p.axml;


public class XmlEndNamespace extends AbstractXmlNamespace {

    public XmlEndNamespace() {
        super(Axml.CHUNK_XML_END_NAMESPACE);
    }
    public String toString() {
        if (this.prefix == null || this.uri == null) {
            return "END NS";
        }
        return "END xmlns:" + this.prefix.data + this.uri.data;
    }
}
