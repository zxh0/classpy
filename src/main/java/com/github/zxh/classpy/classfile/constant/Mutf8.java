package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.helper.Mutf8Decoder;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import java.io.IOException;

/**
 * UTF8 String in constant pool.
 */
public class Mutf8 extends ClassComponent {

    private final U2 length;
    private String value;

    public Mutf8(U2 length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        byte[] bytes = reader.readBytes(length.getValue());
        try {
            value = Mutf8Decoder.decodeMutf8(bytes);
        } catch (IOException e) {
            throw new ClassParseException(e);
        }
        
        setDesc(value);
    }
    
}
