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

    private U4 bytes;

    public U4 getBytes() {
        return bytes;
    }
    
    @Override
    protected void readInfo(ClassReader reader) {
        bytes = reader.readU4();
    }
    
}
