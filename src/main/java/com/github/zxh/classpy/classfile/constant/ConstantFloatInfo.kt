package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
class ConstantFloatInfo : ConstantInfo() {

    init {
        u4("bytes");
    }
    
    override fun loadDesc(cp: ConstantPool): String {
        val f = java.lang.Float.intBitsToFloat(super.getInt("bytes"));
        return f.toString();
    }
    
}
