package com.github.zxh.classpy.lua53.binarychunk.datatype;

import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkReader;

/**
 * lua_Integer - /lua/src/ldump.c#DumpInteger()
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
