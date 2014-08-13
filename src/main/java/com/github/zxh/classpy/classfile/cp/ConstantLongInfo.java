package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;

/*
CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
public class ConstantLongInfo extends ConstantInfo {

    private U4 highBytes;
    private U4 lowBytes;
    private long value;
    
    @Override
    public void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getLong(reader.position());
        highBytes = reader.readU4();
        lowBytes = reader.readU4();
    }
    
}
