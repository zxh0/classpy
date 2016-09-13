package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.datatype.U2;

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
    protected String loadDesc(ConstantPool pool) {
        int classIndex = super.getUInt("class_index");
        int nameAndTypeIndex = super.getUInt("name_and_type_index");

        ConstantClassInfo classInfo = pool.getClassInfo(classIndex);
        String className = pool.getUtf8String(classInfo.getNameIndex());
        ConstantNameAndTypeInfo nameAndTypeInfo = pool.getNameAndTypeInfo(nameAndTypeIndex);
        String fieldName = pool.getUtf8String(nameAndTypeInfo.getNameIndex());
        return className + "." + fieldName;
    }
    
}
