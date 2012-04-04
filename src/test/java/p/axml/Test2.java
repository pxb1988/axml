package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import p.axml.n.AxmlReader;
import p.axml.n.AxmlWriter;
import p.axml.n.DumpAdapter;
import p.axml.n.XAdapter;

import com.googlecode.dex2jar.reader.io.LeArrayDataIn;

public class Test2 {
    @Test
    public void test() throws Exception {
        for (File file : new File("src/test/resources/").listFiles()) {
            if (file.getName().endsWith(".axml")) {
                InputStream is = new FileInputStream(file);
                byte[] xml = new byte[is.available()];
                is.read(xml);
                is.close();
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                AxmlReader rd = new AxmlReader(new LeArrayDataIn(xml));
                AxmlWriter wr = new AxmlWriter();
                rd.accept(new DumpAdapter(wr));
                new AxmlReader(new LeArrayDataIn(wr.toByteArray())).accept(new DumpAdapter(new XAdapter()));
            }
        }
    }

}
