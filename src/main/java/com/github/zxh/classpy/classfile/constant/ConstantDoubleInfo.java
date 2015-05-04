package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U4Hex;

/*
CONSTANT_Double_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
public class ConstantDoubleInfo extends ConstantInfo {

    private U4Hex highBytes;
    private U4Hex lowBytes;
    private double value;
    
    @Override
    protected void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getDouble(reader.getPosition());
        highBytes = reader.readU4Hex();
        lowBytes = reader.readU4Hex();
    }

    @Override
    protected String loadDesc(ConstantPool pool) {
        return String.valueOf(value);
    }
    
}
