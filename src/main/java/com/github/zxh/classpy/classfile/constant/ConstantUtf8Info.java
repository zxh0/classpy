package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.helper.Util;

/*
CONSTANT_Utf8_info {
    u1 tag;
    u2 length;
    u1 bytes[length];
}
*/
public class ConstantUtf8Info extends ConstantInfo {

    private U2 length;
    private Mutf8 bytes;
    
    public String getString() {
        return bytes.getValue();
    }
    
    @Override
    protected void readInfo(ClassReader reader) {
        length = reader.readU2();
        bytes = new Mutf8(length.getValue());
        bytes.read(reader);
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return Util.cutAndAppendEllipsis(bytes.getDesc(), 100);
    }
    
}
