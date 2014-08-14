package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;
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

    private U4 highBytes;
    private U4 lowBytes;
    private long value;

    public U4 getHighBytes() {return highBytes;}
    public U4 getLowBytes() {return lowBytes;}
    
    @Override
    protected void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getLong(reader.getPosition());
        highBytes = reader.readU4();
        highBytes.useHexDesc();
        lowBytes = reader.readU4();
        lowBytes.useHexDesc();
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, highBytes, lowBytes);
    }
    
}
