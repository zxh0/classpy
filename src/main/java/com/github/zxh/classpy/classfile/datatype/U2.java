package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 * Unsigned two-byte quantity.
 *
 * @author zxh
 */
public class U2 extends UInt {

    @Override
    protected int readUInt(ClassReader reader) {
        return reader.readUnsignedShort();
    }
    
}
