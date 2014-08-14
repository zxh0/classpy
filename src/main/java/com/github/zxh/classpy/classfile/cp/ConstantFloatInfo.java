package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4Float;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantFloatInfo extends ConstantInfo {

    private U4Float bytes;

    public U4Float getBytes() {
        return bytes;
    }

    @Override
    protected void readInfo(ClassReader reader) {
        bytes = new U4Float();
        bytes.read(reader);
    }
    
}
