package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.reader.ClassReader;

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
    
}
