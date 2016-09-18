package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}
*/
class ConstantNameAndTypeInfo : ConstantInfo() {

    init {
        u2("name_index");
        u2("descriptor_index");
    }

    fun getNameIndex(): Int {
        return super.getInt("name_index");
    }

    override fun loadDesc(cp: ConstantPool): String {
        val name = cp.getUtf8String(super.getInt("name_index"));
        val type = cp.getUtf8String(super.getInt("descriptor_index"));
        return name + "&" + type;
    }
    
}
