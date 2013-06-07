package pxb.android.axml.test;

import java.io.IOException;

import org.junit.Test;

import pxb.android.axml.AxmlReader;
import pxb.android.axml.AxmlVisitor.NodeVisitor;
import pxb.android.axml.AxmlWriter;
import pxb.android.axml.DumpAdapter;

public class Test4 {
    @Test
    public void test() throws IOException {
        AxmlWriter aw = new AxmlWriter();
        NodeVisitor nv = aw.first("http://abc.com", "abc");
        nv.end();
        nv = aw.first("http://efg.com", "efg");
        nv.end();
        aw.end();
        AxmlReader ar = new AxmlReader(aw.toByteArray());
        ar.accept(new DumpAdapter());
    }

    @Test
    public void test2() throws IOException {
        AxmlWriter aw = new AxmlWriter();
        aw.ns("efg", "http://abc.com", -1);
        NodeVisitor nv = aw.first("http://abc.com", "abc");
        nv.end();
        nv = aw.first("http://efg.com", "efg");
        nv.end();
        aw.end();
        AxmlReader ar = new AxmlReader(aw.toByteArray());
        ar.accept(new DumpAdapter());
    }
}
