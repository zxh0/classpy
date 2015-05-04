package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.attribute.AttributeContainer;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;

/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class FieldInfo extends ClassComponent implements AttributeContainer {

    private U2 accessFlags;
    private U2CpIndex nameIndex;
    private U2CpIndex descriptorIndex;
    private U2 attributesCount;
    private Table<AttributeInfo> attributes;

    @Override
    public Table<AttributeInfo> getAttributes() {
        return attributes;
    }

    @Override
    protected void readContent(ClassReader reader) {
        accessFlags = reader.readU2();
        nameIndex = reader.readU2CpIndex();
        descriptorIndex = reader.readU2CpIndex();
        attributesCount = reader.readU2();
        attributes = reader.readTable(AttributeInfo.class, attributesCount);
        if (nameIndex.getValue() > 0) {
            // todo fix loading java.lang.String from rt.jar
            setDesc(reader.getConstantPool().getUtf8String(nameIndex));
        }
        describe(accessFlags);
    }
    
    protected void describe(U2 accessFlags) {
        AccessFlags.describeFieldFlags(accessFlags);
    }
    
}
