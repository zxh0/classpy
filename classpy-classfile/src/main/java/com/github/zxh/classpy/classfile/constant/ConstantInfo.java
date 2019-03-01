package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassFilePart;

/*
cp_info {
    u1 tag;
    u1 info[];
}
 */
public abstract class ConstantInfo extends ClassFilePart {

    {
        u1("tag");
    }

    protected abstract String loadDesc(ConstantPool cp);
    
}
