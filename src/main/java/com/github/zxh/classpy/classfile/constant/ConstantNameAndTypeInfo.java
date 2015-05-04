package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}
*/
public class ConstantNameAndTypeInfo extends ConstantInfo {

    private U2 nameIndex;
    private U2 descriptorIndex;

    public U2 getNameIndex() {
        return nameIndex;
    }
    
    @Override
    protected void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        String name = pool.getUtf8String(nameIndex);
        String type = pool.getUtf8String(descriptorIndex);
        return name + "&" + type;
    }
    
}
