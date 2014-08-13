package com.github.zxh.classpy.classfile;

/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class FieldInfo extends ClassComponent {

    private U2 accessFlags;
    private U2 nameIndex;
    private U2 descriptorIndex;
    private U2 attributesCount;
    
    @Override
    public void readContent(ClassReader reader) {
        // todo
    }
    
}
