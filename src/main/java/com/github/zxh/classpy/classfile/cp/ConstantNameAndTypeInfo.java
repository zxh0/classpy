package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}
*/
public class ConstantNameAndTypeInfo extends ConstantInfo {

    private U2 nameIndex;
    private U2 descriptorIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
    }
    
}
