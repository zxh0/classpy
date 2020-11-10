package com.github.zxh.classpy.lua54.binarychunk.datatype;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;

public class VarInt extends BinaryChunkPart {

    private int value;

    public int getValue() {
        return value;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readVarInt();
        setDesc(Long.toString(value));
    }

}
