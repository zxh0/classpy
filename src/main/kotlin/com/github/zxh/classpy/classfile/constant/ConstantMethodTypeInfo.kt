package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}
*/
class ConstantMethodTypeInfo : ConstantInfo() {

    init {
        u2("descriptor_index");
    }
    
    override fun loadDesc(cp: ConstantPool): String {
        val descriptorIndex = super.getInt("descriptor_index");
        return cp.getUtf8String(descriptorIndex);
    }
    
}
