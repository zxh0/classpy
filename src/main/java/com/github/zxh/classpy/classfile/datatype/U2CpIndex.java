package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 * Same as U2, but used as index of ConstantPool.
 */
public class U2CpIndex extends U2 {

    @Override
    protected void describe(int value, ClassReader reader) {
        if (value > 0) {
            String constantDesc = reader.getConstantPool().getConstantDesc(value);
            setDesc("#" + value + "->" + constantDesc);
        } else {
            setDesc("#" + value);
        }
    }
    
}
