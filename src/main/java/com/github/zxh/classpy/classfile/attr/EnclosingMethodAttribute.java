package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
EnclosingMethod_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 class_index;
    u2 method_index;
}
 */
public class EnclosingMethodAttribute extends AttributeInfo {

    private U2 classIndex;
    private U2 methodIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        classIndex = reader.readU2();
        methodIndex = reader.readU2();
    }
    
}
