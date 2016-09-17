package com.github.zxh.classpy.classfile.attribute;

/*
ConstantValue_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 constantvalue_index;
}
 */
class ConstantValueAttribute : AttributeInfo() {

    init {
        u2cp("constant_value_index");
    }
    
}
