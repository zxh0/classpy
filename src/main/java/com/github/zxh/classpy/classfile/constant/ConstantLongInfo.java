package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4Hex;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
public class ConstantLongInfo extends ConstantInfo {

    private U4Hex highBytes;
    private U4Hex lowBytes;
    private long value;

    @Override
    protected void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getLong(reader.getPosition());
        highBytes = reader.readU4Hex();
        lowBytes = reader.readU4Hex();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return String.valueOf(value);
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, highBytes, lowBytes);
    }
    
}
