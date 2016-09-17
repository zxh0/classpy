package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.attribute.RuntimeVisibleAnnotationsAttribute.ElementValue;

/*
AnnotationDefault_attribute {
    u2            attribute_name_index;
    u4            attribute_length;
    element_value default_value;
}
 */
class AnnotationDefaultAttribute : AttributeInfo() {

    init {
        add("default_value", ElementValue());
    }

}
