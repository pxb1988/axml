package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.googlecode.dex2jar.reader.io.LeArrayDataIn;
import com.googlecode.dex2jar.reader.io.LeDataOut;

public class Test1 {
    @Test
    public void test() throws Exception {
        InputStream is = Test1.class.getResourceAsStream("/a.axml");
        byte[] xml = new byte[is.available()];
        is.read(xml);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder();
        List<Item> items = Axml.read(new LeArrayDataIn(xml));
        for (Item item : items) {
            sb.append(item).append("\n");
        }
        Axml.write(items, new LeDataOut(os));
        os.close();
        items = Axml.read(new LeArrayDataIn(os.toByteArray()));
        StringBuilder sb1 = new StringBuilder();
        for (Item item : items) {
            sb1.append(item).append("\n");
        }
        Assert.assertEquals("", sb.toString(), sb1.toString());
    }
}
