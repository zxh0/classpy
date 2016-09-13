package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;

/**
 *              UInt
 *            /  |   \
 *          U1   U2   U4
 *         /     |      \
 *  U1CpIndex U2CpIndex U4Hex
 */
public abstract class UInt extends ClassComponent {

    protected int value;
    
    public final int getValue() {
        return value;
    }

    @Override
    protected void afterRead(ConstantPool cp) {
        setDesc(Integer.toString(value));
    }
    
}
