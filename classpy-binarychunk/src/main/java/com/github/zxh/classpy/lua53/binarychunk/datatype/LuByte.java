package com.github.zxh.classpy.lua53.binarychunk.datatype;

import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkReader;

/**
 * lu_byte - /lua/src/ldump.c#DumpByte()
 */
public class LuByte extends BinaryChunkPart {

    private int value;

    public int getValue() {
        return value;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readUnsignedByte();
        super.setDesc(Integer.toString(value));

        // TODO
        if (super.getName() != null) {
            switch (super.getName()) {
                case "sizeof(int)"         -> reader.setCIntSize(value);
                case "sizeof(size_t)"      -> reader.setSizetSize(value);
                case "sizeof(lua_Integer)" -> reader.setLuaIntSize(value);
                case "sizeof(lua_Number)"  -> reader.setLuaNumSize(value);
            }
        }
    }

}
