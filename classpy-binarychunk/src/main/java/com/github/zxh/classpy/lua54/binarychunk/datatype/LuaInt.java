package com.github.zxh.classpy.lua54.binarychunk.datatype;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;

/**
 * lua_Integer
 */
public class LuaInt extends BinaryChunkPart {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readLuaInt();
        setDesc(Long.toString(value));
    }

}
