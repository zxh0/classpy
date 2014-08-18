package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2CpIndex;
import java.util.Arrays;
import java.util.List;

/*
SourceFile_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 sourcefile_index;
}
 */
public class SourceFileAttribute extends AttributeInfo {

    private U2CpIndex sourceFileIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        sourceFileIndex = reader.readU2CpIndex();
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength,
                sourceFileIndex);
    }
    
}
