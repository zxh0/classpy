package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassFilePart;
import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.jvm.Mutf8Decoder;
import com.github.zxh.classpy.helper.StringHelper;

import java.io.IOException;

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
        return ((Mutf8) super.get("bytes")).str;
    }

    @Override
    protected String loadDesc(ConstantPool cp) {
        Mutf8 bytes = (Mutf8) super.get("bytes");
        return StringHelper.cutAndAppendEllipsis(bytes.getDesc(), 100);
    }


    // UTF8 String in constant pool.
    private class Mutf8 extends ClassFilePart {

        private final U2 length;
        private String str;

        public Mutf8(U2 length) {
            this.length = length;
        }

        @Override
        protected void readContent(ClassFileReader reader) {
            byte[] bytes = reader.readBytes(length.getValue());
            try {
                str = Mutf8Decoder.decodeMutf8(bytes);
            } catch (IOException e) {
                throw new ParseException(e);
            }

            setDesc(str);
        }

    }

}
