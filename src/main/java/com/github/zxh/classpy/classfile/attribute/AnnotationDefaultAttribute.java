package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.attribute.RuntimeVisibleAnnotationsAttribute.ElementValue;

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
    
}
