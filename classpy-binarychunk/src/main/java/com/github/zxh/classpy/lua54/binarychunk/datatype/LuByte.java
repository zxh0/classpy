package com.github.zxh.classpy.lua54.binarychunk.datatype;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;

/**
 * lu_byte
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
                case "sizeof(Instruction)":
                    reader.setInstrSize(value);
                    break;
                case "sizeof(lua_Integer)":
                    reader.setLuaIntSize(value);
                    break;
                case "sizeof(lua_Number)":
                    reader.setLuaNumSize(value);
                    break;
            }
        }
    }

}
