package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U4Float;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantFloatInfo extends ConstantInfo {

    private U4Float bytes;

    @Override
    protected void readInfo(ClassReader reader) {
        bytes = reader.readU4Float();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return bytes.getDesc();
    }
    
}
