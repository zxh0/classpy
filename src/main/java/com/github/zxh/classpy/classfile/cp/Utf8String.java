package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author zxh
 */
public class Utf8String extends ClassComponent {

    private final int length;
    private String value;

    public Utf8String(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        byte[] bytes = reader.readBytes(length);
        value = new String(bytes, StandardCharsets.UTF_8);
        if (value.length() < 100) {
            setDesc(value);
        } else {
            // cut long String
            setDesc(value.substring(0, 100) + "...");
        }
    }
    
}
