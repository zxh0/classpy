package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
open class AttributeInfo : ClassComponent() {

    init {
        u2("attribute_name_index");
        u4("attribute_length");
    }

}
