package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Integer_info {
    u1 tag;
    u4 bytes;
}
*/
class ConstantIntegerInfo : ConstantInfo() {

    init {
        u4("bytes");
    }

    override fun loadDesc(cp: ConstantPool): String {
        val i = super.getInt("bytes");
        return i.toString();
    }
    
}
