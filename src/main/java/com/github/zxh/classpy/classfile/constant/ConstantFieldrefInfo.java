package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
public class ConstantFieldrefInfo extends ConstantInfo {

    private U2 classIndex;
    private U2 nameAndTypeIndex;

    @Override
    protected void readInfo(ClassReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        ConstantClassInfo classInfo = pool.getClassInfo(classIndex);
        String className = pool.getUtf8String(classInfo.getNameIndex());
        ConstantNameAndTypeInfo nameAndTypeInfo = pool.getNameAndTypeInfo(nameAndTypeIndex);
        String fieldName = pool.getUtf8String(nameAndTypeInfo.getNameIndex());
        return className + "." + fieldName;
    }
    
}
