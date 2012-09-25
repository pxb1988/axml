package pxb.android.axml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


public class EnableDebugger {
    // @Test
    // public void test() throws Exception {
    // a(new File("src/test/resources/a.axml"), new File("target/a-debug.axml"));
    // }

    public static void main(String... args) throws Exception {
        if (args.length < 2) {
            System.err.println("test5 in out");
            return;
        }
        new EnableDebugger().a(new File(args[0]), new File(args[1]));
    }

    void a(File a, File b) throws Exception {
        InputStream is = new FileInputStream(a);
        byte[] xml = new byte[is.available()];
        is.read(xml);
        is.close();

        AxmlReader rd = new AxmlReader(xml);
        AxmlWriter wr = new AxmlWriter();
        rd.accept(new AxmlVisitor(wr) {

            @Override
            public NodeVisitor first(String ns, String name) {// manifest
                return new NodeVisitor(super.first(ns, name)) {

                    @Override
                    public NodeVisitor child(String ns, String name) {// application
                        return new NodeVisitor(super.child(ns, name)) {

                            @Override
                            public void attr(String ns, String name, int resourceId, int type, Object obj) {
                                if ("http://schemas.android.com/apk/res/android".equals(ns)
                                        && "debuggable".equals(name)) {
                                    return;
                                }
                                super.attr(ns, name, resourceId, type, obj);
                            }

                            @Override
                            public void end() {
                                // android:debuggable(0x0101000f)=(type 0x12)0xffffffff
                                super.attr("http://schemas.android.com/apk/res/android", "debuggable", 0x0101000f,
                                        0x12, 0xffffffff);
                                super.end();
                            }
                        };
                    }
                };
            }

        });
        byte[] modified = wr.toByteArray();
        FileOutputStream fos = new FileOutputStream(b);
        fos.write(modified);
        fos.close();
    }
}
