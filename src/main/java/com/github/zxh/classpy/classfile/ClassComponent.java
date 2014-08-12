package com.github.zxh.classpy.classfile;

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

    public void read(ClassReader reader) {
        offset = reader.position();
        readContent(reader);
        length = reader.position() - offset;
    }
    
    public abstract void readContent(ClassReader reader);
    
}
