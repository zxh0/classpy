package com.github.zxh.classpy.lua54.binarychunk.datatype;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;

/**
 * lua_Number
 */
public class LuaNum extends BinaryChunkPart {

    private double value;

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readLuaNum();
        setDesc(Double.toString(value));
    }

}
