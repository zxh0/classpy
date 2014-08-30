package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U4;

/*
CONSTANT_Integer_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantIntegerInfo extends ConstantInfo {

    private U4 bytes;

    @Override
    protected void readInfo(ClassReader reader) {
        bytes = reader.readU4();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return String.valueOf(bytes.getValue());
    }
    
}
