package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.googlecode.dex2jar.reader.io.DataIn;
import com.googlecode.dex2jar.reader.io.LeArrayDataIn;
import com.googlecode.dex2jar.reader.io.LeDataOut;

public class Test1 {

    public static void main(String... args) throws Exception {
        InputStream is = new FileInputStream("src/main/resources/AndroidManifest.xml");
        // InputStream is = new FileInputStream("a");
        byte[] xml = new byte[is.available()];
        is.read(xml);
        DataIn in = new LeArrayDataIn(xml);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        List<Item> items = Axml.read(in);
//        for (Item item : items) {
//            System.out.println(item);
//        }
        Axml.write(items, new LeDataOut(os));
        os.close();
        items = Axml.read(new LeArrayDataIn(os.toByteArray()));
        System.out.println("===========");
//        for (Item item : items) {
//            System.out.println(item);
//        }
        FileUtils.writeByteArrayToFile(new File("target/a.xml"), os.toByteArray());

    }

}
