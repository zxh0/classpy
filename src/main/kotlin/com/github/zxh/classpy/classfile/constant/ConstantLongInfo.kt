package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
class ConstantLongInfo : ConstantInfo() {

    init {
        u4hex("high_bytes");
        u4hex("low_bytes");
    }

    override fun loadDesc(cp: ConstantPool): String {
        val high = super.getInt("high_bytes").toLong();
        val low = super.getInt("low_bytes").toLong();
        val l = high shl  32 or low;
        return l.toString();
    }
    
}
