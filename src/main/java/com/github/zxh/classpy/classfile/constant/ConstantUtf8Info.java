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
public class ConstantUtf8Info extends ConstantInfo {

    {
        U2 length = new U2();

        add("length", length);
        add("bytes", new Mutf8(length));
    }

    public String getString() {
        return ((Mutf8) super.get("bytes")).getValue();
    }

    @Override
    protected String loadDesc(ConstantPool cp) {
        Mutf8 bytes = (Mutf8) super.get("bytes");
        return StringHelper.cutAndAppendEllipsis(bytes.getDesc(), 100);
    }
    
}
