package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFileComponent;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
public abstract class AttributeInfo extends ClassFileComponent {

    {
        u2("attribute_name_index");
        u4("attribute_length");
    }

}
