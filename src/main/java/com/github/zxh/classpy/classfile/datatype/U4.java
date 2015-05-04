package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 * Unsigned four-byte quantity.
 * 
 * @author zxh
 */
public class U4 extends UInt {

    @Override
    protected int readUInt(ClassReader reader) {
        int value = reader.readInt();
        if (value < 0) {
            // todo
        }
        return value;
    }
    
}
