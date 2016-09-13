package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}
*/
public class ConstantFloatInfo extends ConstantInfo {

    {
        u4("bytes");
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        float f = Float.intBitsToFloat(super.getUInt("bytes"));
        return Float.toString(f);
    }
    
}
