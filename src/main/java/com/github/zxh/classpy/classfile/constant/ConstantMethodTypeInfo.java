package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}
*/
public class ConstantMethodTypeInfo extends ConstantInfo {

    {
        u2("descriptor_index");
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        int descriptorIndex = super.getUInt("descriptor_index");
        return pool.getUtf8String(descriptorIndex);
    }
    
}
