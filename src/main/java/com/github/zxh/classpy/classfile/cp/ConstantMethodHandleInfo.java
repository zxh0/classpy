package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;
import com.github.zxh.classpy.classfile.U2;

/*
CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}
*/
public class ConstantMethodHandleInfo extends ConstantInfo {

    private U1 referenceKind;
    private U2 referenceIndex;
    
    @Override
    public void readInfo(ClassReader reader) {
        referenceKind = reader.readU1();
        referenceIndex = reader.readU2();
    }
    
}
