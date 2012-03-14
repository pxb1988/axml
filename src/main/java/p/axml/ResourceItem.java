package p.axml;

public class ResourceItem extends Item {
    public int data;
    public int index;
    public String toString() {
        return String.format("R%04d %08x", index, data);
    }
}
