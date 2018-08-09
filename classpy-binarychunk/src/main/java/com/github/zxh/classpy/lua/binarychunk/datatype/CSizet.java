package com.github.zxh.classpy.lua.binarychunk.datatype;

import com.github.zxh.classpy.lua.binarychunk.BinaryChunkComponent;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;

/**
 * c size_t.
 */
public class CSizet extends BinaryChunkComponent {

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
