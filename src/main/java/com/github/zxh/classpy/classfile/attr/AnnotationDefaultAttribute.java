package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.attr.RuntimeVisibleAnnotationsAttribute.ElementValue;
import java.util.Arrays;
import java.util.List;

/*
AnnotationDefault_attribute {
    u2            attribute_name_index;
    u4            attribute_length;
    element_value default_value;
}
 */
public class AnnotationDefaultAttribute extends AttributeInfo {

    private ElementValue defaultValue;
    
    @Override
    protected void readInfo(ClassReader reader) {
        defaultValue = new ElementValue();
        defaultValue.read(reader);
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength, defaultValue);
    }
    
}
