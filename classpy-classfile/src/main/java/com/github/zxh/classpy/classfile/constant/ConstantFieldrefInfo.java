package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
public class ConstantFieldrefInfo extends ConstantInfo {

    {
        u2("class_index");
        u2("name_and_type_index");
    }
    
    @Override
    protected String loadDesc(ConstantPool cp) {
        int classIndex = super.getUInt("class_index");
        int nameAndTypeIndex = super.getUInt("name_and_type_index");

        ConstantClassInfo classInfo = cp.getClassInfo(classIndex);
        String className = cp.getUtf8String(classInfo.getNameIndex());
        ConstantNameAndTypeInfo nameAndTypeInfo = cp.getNameAndTypeInfo(nameAndTypeIndex);
        String fieldName = cp.getUtf8String(nameAndTypeInfo.getNameIndex());
        return className + "." + fieldName;
    }
    
}
