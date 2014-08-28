package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U4Float;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantFloatInfo extends ConstantInfo {

    private U4Float bytes;

    @Override
    protected void readInfo(ClassReader reader) {
        bytes = new U4Float();
        bytes.read(reader);
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return String.valueOf(bytes.getValue());
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, bytes);
    }
    
}
