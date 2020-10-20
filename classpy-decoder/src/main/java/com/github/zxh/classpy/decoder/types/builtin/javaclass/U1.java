package com.github.zxh.classpy.decoder.types.builtin.javaclass;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.decoder.types.builtin.ReadableFilePart;

public class U1 extends ReadableFilePart {

    private int value;

    @Override
    protected void readContent(BytesReader reader) {
        value = reader.readUnsignedByte();
    }

}
