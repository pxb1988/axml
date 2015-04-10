package pxb.android.axml.test;

import org.junit.Test;
import pxb.android.Res_value;
import pxb.android.axml.*;

import java.io.File;
import java.io.IOException;

public class Test2 {
    @Test
    public void test() throws Exception {
        for (File file : new File("src/test/resources/").listFiles()) {
            if (file.getName().endsWith(".axml")) {
                System.out.println("======= test " + file);
                byte[] xml = Util.readFile(file);
                AxmlReader rd = new AxmlReader(xml);
                AxmlWriter wr = new AxmlWriter();
                System.out.println("=== A ");
                rd.accept((new DumpAdapter(wr)));
                System.out.println("=== B ");
                new AxmlReader(wr.toByteArray()).accept(new DumpAdapter(AxmlReader.EMPTY_VISITOR));
            }
        }
    }

    @Test
    public void createAxml() throws IOException {
        AxmlWriter aw = new AxmlWriter();
        aw.ns("android", "http://schemas.android.com/apk/res/android", 0);
        {
            NodeVisitor manifest = aw.child(null, "manifest");
            manifest.attr("http://schemas.android.com/apk/res/android", "versionCode", R.attr.versionCode,
                    null, Res_value.newDecInt(1));
            manifest.attr("http://schemas.android.com/apk/res/android", "versionName", R.attr.versionName, "1.0", Res_value
                    .newStringValue("1.0"));
            manifest.attr(null, "package", 0, "a.b", Res_value.newStringValue("a.b"));
            {
                NodeVisitor app = manifest.child(null, "application");
                app.attr("http://schemas.android.com/apk/res/android", "label", R.attr.label, null, Res_value
                        .newReference(0x7f030000));
                app.attr("http://schemas.android.com/apk/res/android", "debuggable", R.attr.debuggable,
                        null, Res_value.newTrue());
                app.end();
            }
            manifest.end();
        }
        aw.end();
        byte[] data = aw.toByteArray();
        // save data
        new AxmlReader(data).accept(new DumpAdapter());
    }


}
