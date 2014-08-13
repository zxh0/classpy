package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantFloatInfo extends ConstantInfo {

    private U4 bytes;
    private float value;
    
    @Override
    public void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getFloat(reader.position());
        bytes = reader.readU4();
    }
    
}
