package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
public class ConstantFieldrefInfo extends ConstantInfo {

    private U2 classIndex;
    private U2 nameAndTypeIndex;

    @Override
    protected void readInfo(ClassReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, classIndex, nameAndTypeIndex);
    }
    
}
