package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;

/*
cp_info {
    u1 tag;
    u1 info[];
}
 */
abstract class ConstantInfo : ClassComponent() {

    init {
        u1("tag");
    }

    abstract fun loadDesc(cp: ConstantPool): String;
    
}
