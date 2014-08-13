package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attr.AttributeInfo;
import java.util.ArrayList;
import java.util.List;

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
    private final List<AttributeInfo> attributes = new ArrayList<>();
    
    @Override
    protected void readContent(ClassReader reader) {
        accessFlags = reader.readU2();
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
        attributesCount = reader.readU2();
        for (int i = 0; i < attributesCount.getValue(); i++) {
//            reader.getConstantPool().
            
            AttributeInfo attrInfo = null; // todo
            attributes.add(attrInfo);
        }
    }
    
}
