package com.github.zxh.classpy.classfile;

import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public class U1 extends ClassComponent {

    private byte value;
    
    @Override
    public void readContent(ByteBuffer buf) {
        value = buf.get();
    }
    
}
