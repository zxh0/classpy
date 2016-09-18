package com.github.zxh.classpy.classfile.attribute;

/*
EnclosingMethod_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 class_index;
    u2 method_index;
}
 */
class EnclosingMethodAttribute : AttributeInfo() {

    init {
        u2cp("class_index");
        u2cp("method_index");
    }

}
