package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attr.AttributeInfo;

/*
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class MethodInfo extends ClassComponent {

    private U2 accessFlags;
    private U2 nameIndex;
    private U2 descriptorIndex;
    private U2 attributesCount;
    private AttributeInfo[] attributes;
    
    @Override
    protected void readContent(ClassReader reader) {
        accessFlags = reader.readU2();
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
        attributesCount = reader.readU2();
        attributes = new AttributeInfo[attributesCount.getValue()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = reader.readAttributeInfo();
        }
    }
    
}
