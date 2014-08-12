package com.github.zxh.classpy.classfile;

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
    public void readContent(ClassReader reader) {
        value = reader.getByteBuffer().getShort();
    }
    
}
