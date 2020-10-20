package com.github.zxh.classpy.decoder.types.builtin.javaclass;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.decoder.format.FormatException;
import com.github.zxh.classpy.decoder.types.builtin.BuiltinTypeReader;
import com.github.zxh.classpy.decoder.types.builtin.ReadableFilePart;

public class JavaBuiltinTypeReader implements BuiltinTypeReader {

    @Override
    public FilePart read(BytesReader reader, String typeName) {
        ReadableFilePart part = switch (typeName) {
            case "u1" -> new U1();
            case "u2" -> new U2();
            case "u4" -> new U4();
            default -> throw new FormatException("unknown type: " + typeName);
        };
        part.read(reader);
        return part;
    }

}
