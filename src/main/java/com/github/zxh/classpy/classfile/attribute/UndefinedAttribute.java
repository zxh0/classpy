package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassReader;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
public class UndefinedAttribute extends AttributeInfo {

    @Override
    protected void readInfo(ClassReader reader) {
        reader.skipBytes(attributeLength.getValue());
    }
    
}
