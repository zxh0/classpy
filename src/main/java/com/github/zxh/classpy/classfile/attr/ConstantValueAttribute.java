package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2CpIndex;
import java.util.Arrays;
import java.util.List;

/*
ConstantValue_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 constantvalue_index;
}
 */
public class ConstantValueAttribute extends AttributeInfo {

    private U2CpIndex constantValueIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        constantValueIndex = reader.readU2CpIndex();
    }

    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength,
                constantValueIndex);
    }
    
}
