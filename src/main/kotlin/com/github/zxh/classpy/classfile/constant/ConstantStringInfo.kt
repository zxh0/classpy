package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_String_info {
    u1 tag;
    u2 string_index;
}
*/
class ConstantStringInfo : ConstantInfo() {

    init {
        u2("string_index");
    }

    override fun loadDesc(cp: ConstantPool): String {
        val stringIndex = super.getInt("string_index");
        return cp.getUtf8Info(stringIndex).loadDesc(cp);
    }
    
}
