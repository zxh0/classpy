package com.github.zxh.classpy.lua53.binarychunk.datatype;

import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkReader;

/**
 * lua_Number - /lua/src/ldump.c#DumpNumber()
 */
public class LuaNum extends BinaryChunkPart {

    private double value;

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readLuaNum();
        setDesc(Double.toString(value));
    }

}
