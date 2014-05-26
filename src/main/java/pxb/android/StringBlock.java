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
package pxb.android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class StringBlock {
    static final int UTF8_FLAG = 0x00000100;

    public List<StringItem> items = new ArrayList<StringItem>();

    @SuppressWarnings("unused")
    public static Object[] read(ByteBuffer in) throws IOException {
        int trunkOffset = in.position() - 8;
        int stringCount = in.getInt();
        int styleCount = in.getInt();
        int flags = in.getInt();
        int stringDataOffset = in.getInt();
        int stylesDataOffset = in.getInt();
        int strOffsets[] = new int[stringCount];
        String strings[] = new String[stringCount];
        for (int i = 0; i < stringCount; i++) {
            strOffsets[i] = in.getInt();
        }
        int styleOffsets[] = new int[styleCount];
        for (int i = 0; i < styleCount; i++) {
            styleOffsets[i] = in.getInt();
        }
        int base = trunkOffset + stringDataOffset;
        for (int i = 0; i < strOffsets.length; i++) {
            in.position(base + strOffsets[i]);
            String s;

            if (0 != (flags & UTF8_FLAG)) {
                u8length(in); // ignored
                int u8len = u8length(in);
                int start = in.position();
                int blength = u8len;
                while (in.get(start + blength) != 0) {
                    blength++;
                }
                s = new String(in.array(), start, blength, "UTF-8");
            } else {
                int length = u16length(in);
                s = new String(in.array(), in.position(), length * 2, "UTF-16LE");
            }
            strings[i] = s;
        }
        List<StyleSpan>[] styles = new List[styleCount];
        base = trunkOffset + stylesDataOffset;
        for (int i = 0; i < styleCount; i++) {
            in.position(base + styleOffsets[i]);
            List<StyleSpan> spans = null;
            System.out.println(String.format("%d %s", i, strings[i]));
            while (true) {
                int id = in.getInt();
                if (id == -1) {
                    break;
                }
                if (spans == null) {
                    spans = new ArrayList<StyleSpan>();
                }
                StyleSpan span = new StyleSpan();
                spans.add(span);
                span.name = strings[id];
                span.start = in.getInt();
                span.end = in.getInt();
                System.out.println(String.format("%d  [%s %d ~ %d]", i, span.name, span.start, span.end));
            }
            styles[i] = spans;
        }
        return new Object[] { strings, styles };
    }

    static int u16length(ByteBuffer in) {
        int length = in.getShort() & 0xFFFF;
        if (length > 0x7FFF) {
            length = ((length & 0x7FFF) << 8) | (in.getShort() & 0xFFFF);
        }
        return length;
    }

    static int u8length(ByteBuffer in) {
        int len = in.get() & 0xFF;
        if ((len & 0x80) != 0) {
            len = ((len & 0x7F) << 8) | (in.get() & 0xFF);
        }
        return len;
    }

    byte[] stringData;

    public int getSize() {
        return 5 * 4 + items.size() * 4 + stringData.length + 0;// TODO
    }

    public void prepare() throws IOException {
        for (StringItem s : items) {
            if (s.data.length() > 0x7FFF) {
                useUTF8 = false;
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = 0;
        int offset = 0;
        baos.reset();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (StringItem item : items) {
            item.index = i++;
            String stringData = item.data;
            Integer of = map.get(stringData);
            if (of != null) {
                item.dataOffset = of;
            } else {
                item.dataOffset = offset;
                map.put(stringData, offset);
                if (useUTF8) {
                    int length = stringData.length();
                    byte[] data = stringData.getBytes("UTF-8");
                    int u8lenght = data.length;

                    if (length > 0x7F) {
                        offset++;
                        baos.write((length >> 8) | 0x80);
                    }
                    baos.write(length);

                    if (u8lenght > 0x7F) {
                        offset++;
                        baos.write((u8lenght >> 8) | 0x80);
                    }
                    baos.write(u8lenght);
                    baos.write(data);
                    baos.write(0);
                    offset += 3 + u8lenght;
                } else {
                    int length = stringData.length();
                    byte[] data = stringData.getBytes("UTF-16LE");
                    if (length > 0x7FFF) {
                        int x = (length >> 16) | 0x8000;
                        baos.write(x);
                        baos.write(x >> 8);
                        offset += 2;
                    }
                    baos.write(length);
                    baos.write(length >> 8);
                    baos.write(data);
                    baos.write(0);
                    baos.write(0);
                    offset += 4 + data.length;
                }
            }
        }
        // TODO
        stringData = baos.toByteArray();
    }

    private boolean useUTF8 = true;

    public void write(ByteBuffer out) throws IOException {
        out.putInt(items.size());
        out.putInt(0);// TODO style count
        out.putInt(useUTF8 ? UTF8_FLAG : 0);
        out.putInt(7 * 4 + items.size() * 4);
        out.putInt(0);
        for (StringItem item : items) {
            out.putInt(item.dataOffset);
        }
        out.put(stringData);
        // TODO
    }

}
