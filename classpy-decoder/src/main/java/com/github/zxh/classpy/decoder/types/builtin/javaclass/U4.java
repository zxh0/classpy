package com.github.zxh.classpy.decoder.types.builtin.javaclass;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.decoder.types.builtin.ReadableFilePart;

public class U4 extends ReadableFilePart {

    private long value;

    @Override
    protected void readContent(BytesReader reader) {
        value = reader.readUnsignedInt();
    }

}
