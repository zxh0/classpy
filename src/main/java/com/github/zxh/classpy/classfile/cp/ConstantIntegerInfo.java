package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;
import java.util.Arrays;
import java.util.List;

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
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, bytes);
    }
    
}
