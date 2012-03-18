package p.axml;

public class StringItem {
    public String data;
    public int dataOffset;
    public int index;

    public StringItem(String data) {
        super();
        this.data = data;
    }

    public StringItem() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        StringItem b = (StringItem) obj;
        return b.data.equals(data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    public String toString() {
        return String.format("S%04d %s", index, data);
    }

}
