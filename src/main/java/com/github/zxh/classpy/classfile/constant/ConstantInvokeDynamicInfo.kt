package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_InvokeDynamic_info {
    u1 tag;
    u2 bootstrap_method_attr_index;
    u2 name_and_type_index;
}
*/
class ConstantInvokeDynamicInfo : ConstantInfo() {

    init {
        u2("bootstrap_method_attr_index");
        u2("name_and_type_index");
    }
    
    override fun loadDesc(cp: ConstantPool): String {
        val nameAndTypeIndex = super.getInt("name_and_type_index");
        return cp.getNameAndTypeInfo(nameAndTypeIndex).loadDesc(cp);
    }
    
}
