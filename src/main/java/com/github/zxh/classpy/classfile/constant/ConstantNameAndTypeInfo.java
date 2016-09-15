package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}
*/
public class ConstantNameAndTypeInfo extends ConstantInfo {

    {
        u2("name_index");
        u2("descriptor_index");
    }

    public int getNameIndex() {
        return super.getInt("name_index");
    }

    @Override
    protected String loadDesc(ConstantPool cp) {
        String name = cp.getUtf8String(super.getInt("name_index"));
        String type = cp.getUtf8String(super.getInt("descriptor_index"));
        return name + "&" + type;
    }
    
}
