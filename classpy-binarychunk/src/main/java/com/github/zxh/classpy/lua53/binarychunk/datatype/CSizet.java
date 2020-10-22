package com.github.zxh.classpy.lua53.binarychunk.datatype;

import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkReader;

/**
 * c size_t.
 */
public class CSizet extends BinaryChunkPart {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readSizet();
        setDesc(Long.toString(value));
    }

}
