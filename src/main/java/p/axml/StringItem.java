package p.axml;

public class StringItem extends Item {
    public String data;
    public int dataOffset;
    public int index;

    public String toString() {
        return String.format("S%04d %s", index, data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        StringItem b = (StringItem) obj;
        return b.data.equals(data);
    }

}
