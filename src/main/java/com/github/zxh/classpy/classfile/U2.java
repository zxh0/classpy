package com.github.zxh.classpy.classfile;

import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public class U2 extends ClassComponent {

    private short value;

    public short getValue() {
        return value;
    }
    
    @Override
    public void readContent(ByteBuffer buf) {
        value = buf.getShort();
    }
    
}
