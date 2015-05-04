package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 * Unsigned one-byte quantity.
 * 
 * @author zxh
 */
public class U1 extends UInt {

    @Override
    protected int readUInt(ClassReader reader) {
        return reader.readUnsignedByte();
    }
    
}
