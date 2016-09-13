package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.constant.ConstantPool;

/**
 * Same as U1, but used as index of ConstantPool.
 */
public class U1CpIndex extends U1 {

    @Override
    protected void afterRead(ConstantPool cp) {
        if (value > 0) {
            String constantDesc = cp.getConstantDesc(value);
            setDesc("#" + value + "->" + constantDesc);
        } else {
            setDesc("#" + value);
        }
    }
    
}
