package p.axml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.googlecode.dex2jar.reader.io.LeArrayDataIn;
import com.googlecode.dex2jar.reader.io.LeDataOut;

public class ReceiverAdd {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out
                    .println("ReceiverAdd receive.clazz [actions] [category] < old.AndroidManifest.xml > new.AndroidManifest.xml");
            return;
        }
        String receiverName = args[0];
        String[] actionStrings = args.length > 1 ? args[1].split(",") : new String[0];
        String[] categoryStrings = args.length > 2 ? args[2].split(",") : new String[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        {
            byte[] buff = new byte[500];
            InputStream in = System.in;
            for (int count = in.read(buff); count > 0; count = in.read(buff)) {
                baos.write(buff, 0, count);
            }
        }
        FirstTag x = FirstTag.get(Axml.read(new LeArrayDataIn(baos.toByteArray())));
        {
            // manifest
            Tag tag = findOrAddOne(x, null, "application");
            Tag receiver = tag.tag(null, "receiver");
            {
                Attribute name = receiver.attr("http://schemas.android.com/apk/res/android", "name", 0x0101003);
                name.valueType = Axml.TYPE_STRING;
                name.value = new StringItem(receiverName);
                name.toString();
            }
            Tag intentFilter = receiver.tag(null, "intent-filter");
            for (String stringString : actionStrings) {
                Tag action = intentFilter.tag(null, "action");
                {
                    Attribute name = action.attr("http://schemas.android.com/apk/res/android", "name", 0x0101003);
                    name.valueType = Axml.TYPE_STRING;
                    name.value = new StringItem(stringString);
                }
            }
            for (String categoryString : categoryStrings) {
                Tag category = intentFilter.tag(null, "category");
                {
                    Attribute name = category.attr("http://schemas.android.com/apk/res/android", "name", 0x0101003);
                    name.valueType = Axml.TYPE_STRING;
                    name.value = new StringItem(categoryString);
                }
            }
        }
        Axml.write(x.toItemArray(), new LeDataOut(System.out));
        System.out.flush();
    }

    static private Tag findOrAddOne(Tag tag, String ns, String string) {
        for (Tag s : tag.subTags) {
            if (s._this.name.data.equals(string)) {
                if ((ns == null && s._this.namespace == null) || ns != null && ns.equals(s._this.namespace.data)) {
                    return s;
                }
            }
        }
        return tag.tag(ns, string);
    }
}
