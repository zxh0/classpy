package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}
*/
public class ConstantNameAndTypeInfo extends ConstantInfo {

    private U2 nameIndex;
    private U2 descriptorIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
    }
        
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, nameIndex, descriptorIndex);
    }
    
}
