package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.Util;
import java.io.IOException;

/**
 * UTF8 String in constant pool.
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
        try {
            value = Util.decodeMutf8(bytes);
        } catch (IOException e) {
            throw new FileParseException(e);
        }
        
        setDesc(Util.cutAndAppendEllipsis(value, 100));
    }
    
}
