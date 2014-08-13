package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
CONSTANT_Class_info {
    u1 tag;
    u2 name_index;
}
*/
public class ConstantClassInfo extends ConstantInfo {

    private U2 nameIndex;
    
    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
    }
    
}
