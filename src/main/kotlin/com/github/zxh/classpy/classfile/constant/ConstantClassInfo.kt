package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_Class_info {
    u1 tag;
    u2 name_index;
}
*/
class ConstantClassInfo : ConstantInfo() {

    init {
        u2("name_index")
    }

    fun getNameIndex(): Int {
        return super.getInt("name_index")
    }

    override fun loadDesc(cp: ConstantPool): String {
        return cp.getUtf8String(getNameIndex());
    }
    
}
