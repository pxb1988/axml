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
package pxb.android.arsc;

import pxb.android.axml.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * dump an arsc file
 * 
 * @author bob
 * 
 */
public class ArscDumper {
    public static void dump(List<Pkg> pkgs) {
        for (int x = 0; x < pkgs.size(); x++) {
            Pkg pkg = pkgs.get(x);
            dump(pkg);
        }
    }

    public static void dump(Pkg pkg) {
        System.out.println(String.format("  Package id=%d name=%s typeCount=%d", pkg.id, pkg.name,
                pkg.types.size()));
        for (Type type : pkg.types.values()) {
            System.out.println(String.format("    type %d %s", type.id - 1, type.name));

            int resPrefix = pkg.id << 24 | type.id << 16;
            for (int i = 0; i < type.specs.length; i++) {
                ResSpec spec = type.getSpec(i);
                System.out.println(String.format("      spec 0x%08x 0x%08x %s", resPrefix | spec.id, spec.flags,
                        spec.name));
            }
            for (int i = 0; i < type.configs.size(); i++) {
                Config config = type.configs.get(i);
                try {
                    System.out.println("      config" + new ConfigDetail(config.id));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (Map.Entry<ResSpec, ResEntry> e : config.resources.entrySet()) {
                    ResEntry entry = e.getValue();
                    ResSpec spec = e.getKey();
                    System.out.println(String.format("        resource 0x%08x %-20s: %s",
                            resPrefix | spec.id, spec.name, entry.value));
                }
            }
        }
    }

    public static void main(String... args) throws IOException {
        if (args.length == 0) {
            System.err.println("asrc-dump file.arsc");
            return;
        }
        String name = args[0];
        byte[] data;
        if (name.endsWith(".apk") || name.endsWith(".zip") || name.endsWith(".jar")) {
            ZipFile zip = new ZipFile(name);
            ZipEntry e = zip.getEntry("resources.arsc");
            if (e == null) {
                zip.close();
                throw new RuntimeException("can't find resources.arsc in " + name);
            } else {
                data = Util.readIs(zip.getInputStream(e));
                zip.close();
            }
        } else {
            data = Util.readFile(new File(name));
        }
        List<Pkg> pkgs = new ArscParser(data).parse();

        dump(pkgs);
    }
}
