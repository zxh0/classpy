package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.Mutf8Decoder;
import java.io.IOException;

/**
 * UTF8 String in constant pool.
 * 
 * @author zxh
 */
public class Mutf8 extends ClassComponent {

    private final int length;
    private String value;

    public Mutf8(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        byte[] bytes = reader.readBytes(length);
        try {
            value = Mutf8Decoder.decodeMutf8(bytes);
        } catch (IOException e) {
            throw new ClassParseException(e);
        }
        
        setDesc(value);
    }
    
}
