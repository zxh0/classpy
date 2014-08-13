package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.nio.charset.StandardCharsets;

/*
CONSTANT_Utf8_info {
    u1 tag;
    u2 length;
    u1 bytes[length];
}
*/
public class ConstantUtf8Info extends ConstantInfo {

    private U2 length;
    private String value;
    
    public String getValue() {
        return value;
    }
    
    @Override
    protected void readInfo(ClassReader reader) {
        length = reader.readU2();
        byte[] bytes = reader.readBytes(length.getValue());
        value = new String(bytes, StandardCharsets.UTF_8);
    }
    
}
