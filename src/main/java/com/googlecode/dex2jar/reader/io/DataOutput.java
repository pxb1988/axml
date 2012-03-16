package com.googlecode.dex2jar.reader.io;

import java.io.IOException;

public interface DataOutput {
    void writeBytes(byte[] bs) throws IOException;

    void writeByte(int b) throws IOException;

    void writeInt(int i) throws IOException;

    void writeShort(int i) throws IOException;
}
