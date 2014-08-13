package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}

CONSTANT_Methodref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}

CONSTANT_InterfaceMethodref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
public class ConstantXXXrefInfo extends ConstantInfo {

    private U2 classIndex;
    private U2 nameAndTypeIndex;
    
    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }
    
}
