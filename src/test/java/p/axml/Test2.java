package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import p.axml.n.AxmlReader;
import p.axml.n.AxmlWriter;
import p.axml.n.DumpAdapter;
import p.axml.n.EmptyAdapter;

import com.googlecode.dex2jar.reader.io.LeArrayDataIn;

public class Test2 {
    @Test
    public void test() throws Exception {
        for (File file : new File("src/test/resources/").listFiles()) {
            if (file.getName().endsWith(".axml")) {
                System.out.println("======= test " + file);
                InputStream is = new FileInputStream(file);
                byte[] xml = new byte[is.available()];
                is.read(xml);
                is.close();
                AxmlReader rd = new AxmlReader(new LeArrayDataIn(xml));
                AxmlWriter wr = new AxmlWriter();
                System.out.println("=== A ");
                rd.accept(new DumpAdapter(wr));
                System.out.println("=== B ");
                new AxmlReader(new LeArrayDataIn(wr.toByteArray())).accept(new DumpAdapter(new EmptyAdapter()));
            }
        }
    }

}
