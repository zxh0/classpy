package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.helper.StringHelper;

/*
CONSTANT_Utf8_info {
    u1 tag;
    u2 length;
    u1 bytes[length];
}
*/
class ConstantUtf8Info : ConstantInfo() {

    init {
        val length = U2();

        add("length", length);
        add("bytes", Mutf8(length));
    }

    fun getString(): String {
        return (super.get("bytes") as Mutf8).value;
    }

    override fun loadDesc(cp: ConstantPool): String {
        val bytes = super.get("bytes") as Mutf8
        return StringHelper.cutAndAppendEllipsis(bytes.desc!!, 100);
    }
    
}
