package com.github.zxh.classpy.classfile;

/**
 * Unsigned four-byte quantity.
 * 
 * @author zxh
 */
public class U4 extends ClassComponent {

    private int value;

    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        value = reader.getByteBuffer().getInt();
        if (value < 0) {
            // todo
        }
        setDesc(String.valueOf(value));
    }
    
}
