package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 * Unsigned four-byte quantity.
 */
public class U4 extends UInt {

    @Override
    protected void readContent(ClassReader reader) {
        value = reader.readInt();
        if (value < 0) {
            // todo
        }
    }
    
}
