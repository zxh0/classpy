package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.Arrays;
import java.util.List;

/*
Deprecated_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
}
 */
public class DeprecatedAttribute extends AttributeInfo {

    @Override
    protected void readInfo(ClassReader reader) {
        //
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength);
    }
    
}
