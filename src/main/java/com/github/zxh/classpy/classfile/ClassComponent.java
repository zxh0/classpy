package com.github.zxh.classpy.classfile;

import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public abstract class ClassComponent {
    
    private int offset;
    private int length;

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public void read(ByteBuffer buf) {
        offset = buf.position();
        readContent(buf);
        length = buf.position() - offset;
    }
    
    public abstract void readContent(ByteBuffer buf);
    
}
