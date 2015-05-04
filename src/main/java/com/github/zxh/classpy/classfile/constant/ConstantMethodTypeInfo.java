package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}
*/
public class ConstantMethodTypeInfo extends ConstantInfo {

    private U2 descriptorIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        descriptorIndex = reader.readU2();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return pool.getUtf8String(descriptorIndex);
    }
    
}
