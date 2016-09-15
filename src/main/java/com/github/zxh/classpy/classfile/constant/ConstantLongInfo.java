package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
public class ConstantLongInfo extends ConstantInfo {

    {
        u4hex("high_bytes");
        u4hex("low_bytes");
    }

    @Override
    protected String loadDesc(ConstantPool cp) {
        long high = super.getInt("high_bytes");
        long low = super.getInt("low_bytes");
        long l = high << 32 | low;
        return String.valueOf(l);
    }
    
}
