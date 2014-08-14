package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
CONSTANT_String_info {
    u1 tag;
    u2 string_index;
}
*/
public class ConstantStringInfo extends ConstantInfo {

    private U2 stringIndex;

    public U2 getStringIndex() {
        return stringIndex;
    }
    
    @Override
    protected void readInfo(ClassReader reader) {
        stringIndex = reader.readU2();
    }
    
}
