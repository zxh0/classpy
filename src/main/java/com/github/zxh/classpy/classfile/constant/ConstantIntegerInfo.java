package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Integer_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantIntegerInfo extends ConstantInfo {

    {
        u4("bytes");
    }

    @Override
    protected String loadDesc(ConstantPool cp) {
        int i = super.getUInt("bytes");
        return String.valueOf(i);
    }
    
}
