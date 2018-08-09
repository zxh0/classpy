package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_String_info {
    u1 tag;
    u2 string_index;
}
*/
public class ConstantStringInfo extends ConstantInfo {

    {
        u2("string_index");
    }

    @Override
    protected String loadDesc(ConstantPool cp) {
        int stringIndex = super.getUInt("string_index");
        return cp.getUtf8Info(stringIndex).loadDesc(cp);
    }
    
}
