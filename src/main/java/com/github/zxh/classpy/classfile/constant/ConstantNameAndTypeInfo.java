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
        return super.getUInt("name_index");
    }

    @Override
    protected String loadDesc(ConstantPool pool) {
        String name = pool.getUtf8String(super.getUInt("name_index"));
        String type = pool.getUtf8String(super.getUInt("descriptor_index"));
        return name + "&" + type;
    }
    
}
