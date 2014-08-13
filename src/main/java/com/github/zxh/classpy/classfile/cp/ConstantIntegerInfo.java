package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;

/*
CONSTANT_Integer_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantIntegerInfo extends ConstantInfo {

    private U4 intValue;
    
    @Override
    public void readInfo(ClassReader reader) {
        intValue = reader.readU4();
    }
    
}
