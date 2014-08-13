package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U1 extends ClassComponent {

    private int value;
    
    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        byte b = reader.getByteBuffer().get();
        value = Byte.toUnsignedInt(b);
    }
    
}
