package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.datatype.U2;

/*
CONSTANT_InvokeDynamic_info {
    u1 tag;
    u2 bootstrap_method_attr_index;
    u2 name_and_type_index;
}
*/
public class ConstantInvokeDynamicInfo extends ConstantInfo {

    {
        u2("bootstrap_method_attr_index");
        u2("name_and_type_index");
    }
    
    @Override
    protected String loadDesc(ConstantPool cp) {
        int nameAndTypeIndex = super.getUInt("name_and_type_index");
        return cp.getNameAndTypeInfo(nameAndTypeIndex).loadDesc(cp);
    }
    
}
