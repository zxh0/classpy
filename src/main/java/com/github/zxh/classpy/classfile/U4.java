package com.github.zxh.classpy.classfile;

import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public class U4 extends ClassComponent {

    private int value;
    
    @Override
    public void readContent(ByteBuffer buf) {
        value = buf.getInt();
    }
    
}
