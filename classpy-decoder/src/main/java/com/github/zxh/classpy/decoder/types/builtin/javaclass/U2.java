package com.github.zxh.classpy.decoder.types.builtin.javaclass;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.decoder.types.builtin.IntValue;
import com.github.zxh.classpy.decoder.types.builtin.ReadableFilePart;

public class U2 extends ReadableFilePart implements IntValue {

    private int value;

    @Override
    protected void readContent(BytesReader reader) {
        value = reader.readUnsignedShort();
    }

    @Override
    public int getValue() {
        return value;
    }

}
