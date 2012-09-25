/*
 * Copyright (c) 2009-2012 Panxiaobo
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
package com.googlecode.dex2jar.reader.io;

/**
 * 输入流
 * 
 * @author <a href="mailto:pxb1988@gmail.com">Panxiaobo</a>
 * @version $Rev: 3b20152caede $
 */
public interface DataIn {

    /**
     * 获取当前位置
     * 
     * @return
     */
    int getCurrentPosition();

    void move(int absOffset);

    void pop();

    void push();

    /**
     * equals to
     * 
     * <pre>
     * push();
     * move(absOffset);
     * </pre>
     * 
     * @see #push()
     * @see #move(int)
     * @param absOffset
     */
    void pushMove(int absOffset);

    /**
	 * 
	 */
    int readByte();

    byte[] readBytes(int size);

    int readIntx();

    int readUIntx();

    int readShortx();

    int readUShortx();

    long readLeb128();

    /**
     * @return
     */
    int readUByte();

    long readULeb128();

    /**
     * @param i
     */
    void skip(int bytes);

}
