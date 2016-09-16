package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.helper.Mutf8Decoder;
import java.io.IOException;

/**
 * UTF8 String in constant pool.
 */
class Mutf8(val length: U2) : ClassComponent() {

    var value: String = "";
    
    override fun readContent(reader: ClassReader) {
        val bytes = reader.readBytes(length.value);
        try {
            value = Mutf8Decoder.decodeMutf8(bytes);
        } catch (e: IOException) {
            throw ClassParseException(e);
        }

        desc = value;
    }
    
}
