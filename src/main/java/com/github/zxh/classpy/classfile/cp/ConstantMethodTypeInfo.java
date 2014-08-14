package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}
*/
public class ConstantMethodTypeInfo extends ConstantInfo {

    private U2 descriptorIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        descriptorIndex = reader.readU2();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return pool.getUtf8String(descriptorIndex);
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, descriptorIndex);
    }
    
}
