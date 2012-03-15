package p.axml;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class LeDataOut implements DataOutput {

    public LeDataOut(OutputStream os) {
        super();
        this.os = os;
    }

    private OutputStream os;

    @Override
    public void write(int b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeBoolean(boolean v) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeByte(int v) throws IOException {
        os.write(v);
    }

    @Override
    public void writeShort(int v) throws IOException {
        os.write(v);
        os.write(v >> 8);
    }

    @Override
    public void writeChar(int v) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeInt(int v) throws IOException {
        os.write(v);
        os.write(v >> 8);
        os.write(v >> 16);
        os.write(v >>> 24);
    }

    @Override
    public void writeLong(long v) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeFloat(float v) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeDouble(double v) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeBytes(String s) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeChars(String s) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void writeUTF(String s) throws IOException {
        throw new RuntimeException();
    }

}
