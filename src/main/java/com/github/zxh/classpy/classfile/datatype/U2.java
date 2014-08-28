package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;

/**
 * Unsigned two-byte quantity.
 *
 * @author zxh
 */
public class U2 extends ClassComponent {

    private int value;

    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        short s = reader.getByteBuffer().getShort();
        value = Short.toUnsignedInt(s);
        setDesc(String.valueOf(value));
    }
    
}
