package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 * Unsigned two-byte quantity.
 */
public class U2 extends UInt {

    @Override
    protected void readContent(ClassReader reader) {
        value = reader.readUnsignedShort();
    }
    
}
