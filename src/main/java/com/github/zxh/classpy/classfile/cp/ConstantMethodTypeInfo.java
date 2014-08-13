package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}
*/
public class ConstantMethodTypeInfo extends ConstantInfo {

    private U2 descriptorIndex;
    
    @Override
    public void readInfo(ClassReader reader) {
        descriptorIndex = reader.readU2();
    }
    
}
