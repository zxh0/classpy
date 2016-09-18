package com.github.zxh.classpy.classfile.datatype;

/**
 * Same as U1, but used as index of ConstantPool.
 */
public class U1CpIndex extends UInt {

    public U1CpIndex() {
        super(READ_U1, TO_CONST);
    }

}
