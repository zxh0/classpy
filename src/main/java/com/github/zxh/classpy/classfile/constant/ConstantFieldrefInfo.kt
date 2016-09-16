package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
class ConstantFieldrefInfo : ConstantInfo() {

    init {
        u2("class_index");
        u2("name_and_type_index");
    }
    
    override fun loadDesc(cp: ConstantPool): String {
        val classIndex = super.getInt("class_index");
        val nameAndTypeIndex = super.getInt("name_and_type_index");

        val classInfo = cp.getClassInfo(classIndex);
        val className = cp.getUtf8String(classInfo.getNameIndex());
        val nameAndTypeInfo = cp.getNameAndTypeInfo(nameAndTypeIndex);
        val fieldName = cp.getUtf8String(nameAndTypeInfo.getNameIndex());
        return className + "." + fieldName;
    }
    
}
