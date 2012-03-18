package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import com.googlecode.dex2jar.reader.io.LeArrayDataIn;
import com.googlecode.dex2jar.reader.io.LeDataOut;

public class RW {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        {
            byte[] buff = new byte[500];
            InputStream in = System.in;
            for (int count = in.read(buff); count > 0; count = in.read(buff)) {
                baos.write(buff, 0, count);
            }
        }
        List<Item> items = Axml.read(new LeArrayDataIn(baos.toByteArray()));
        Axml.write(items, new LeDataOut(System.out));
        System.out.flush();
    }
}
