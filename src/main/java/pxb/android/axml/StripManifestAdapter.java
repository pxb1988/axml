/*
 * Copyright (c) 2009-2013 Panxiaobo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pxb.android.axml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StripManifestAdapter extends AxmlVisitor {
    private static String Android_NS = "http://schemas.android.com/apk/res/android";

    public static void main(String... args) throws Exception {
        if (args.length < 2) {
            System.err.println("StripManifestAdapter in out");
            return;
        }
        InputStream is = new FileInputStream(new File(args[0]));
        byte[] xml = new byte[is.available()];
        is.read(xml);
        is.close();

        AxmlReader rd = new AxmlReader(xml);
        AxmlWriter wr = new AxmlWriter();
        rd.accept(new StripManifestAdapter(wr));

        byte[] modified = wr.toByteArray();
        FileOutputStream fos = new FileOutputStream(new File(args[1]));
        fos.write(modified);
        fos.close();
    }

    public static class StripManifestNodeAdapter extends NodeVisitor {

        public StripManifestNodeAdapter() {
            super();
        }

        public StripManifestNodeAdapter(NodeVisitor nv) {
            super(nv);
        }

        @Override
        public void attr(String ns, String name, int resourceId, int type, Object obj) {
            if (resourceId != -1) {
                super.attr("", "", resourceId, type, obj);
            } else {
                super.attr(ns, name, resourceId, type, obj);
            }
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            NodeVisitor nv = super.child(ns, name);
            if (nv != null) {
                nv = new StripManifestNodeAdapter(nv);
            }
            return nv;
        }
    }

    public StripManifestAdapter() {
        super();
    }

    public StripManifestAdapter(AxmlVisitor av) {
        super(av);
    }

    @Override
    public NodeVisitor first(String ns, String name) {
        NodeVisitor nv = super.first(ns, name);
        if (nv != null) {
            nv = new StripManifestNodeAdapter(nv);
        }
        return nv;
    }

    @Override
    public void ns(String prefix, String uri, int ln) {
        if (uri.equals(Android_NS)) {
            return;
        }
        prefix = "";
        super.ns(prefix, uri, ln);
    }

}
