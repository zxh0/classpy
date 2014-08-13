package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassReader;

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
