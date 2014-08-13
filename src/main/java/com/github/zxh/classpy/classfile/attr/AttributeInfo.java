package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.U4;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
public abstract class AttributeInfo extends ClassComponent {

    private U2 attributeNameIndex;
    private U4 attributeLength;
    
    @Override
    protected void readContent(ClassReader reader) {
        attributeNameIndex = reader.readU2();
        attributeLength = reader.readU4();
        readInfo(reader);
    }
    
    protected abstract void readInfo(ClassReader reader);
    
}
