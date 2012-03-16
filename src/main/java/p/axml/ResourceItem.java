package p.axml;

public class ResourceItem {
    public int data;
    public int index;

    public String toString() {
        return String.format("R%04d %08x", index, data);
    }
}
