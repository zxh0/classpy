package com.github.zxh.classpy.classfile;

import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public class ClassReader {

    private final ByteBuffer buf;
    
    public ClassReader(ByteBuffer buf) {
        this.buf = buf;
    }
    
    public U1 readU1() {
        U1 u1 = new U1();
        u1.read(buf);
        return u1;
    }
    
    public U2 readU2() {
        U2 u2 = new U2();
        u2.read(buf);
        return u2;
    }
    
    public U4 readU4() {
        U4 u4 = new U4();
        u4.read(buf);
        return u4;
    }
    
}
