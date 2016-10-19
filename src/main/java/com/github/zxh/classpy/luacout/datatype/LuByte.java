package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * typedef unsigned char lu_byte;
 *
 * @see /lua/src/llimits.h
 * @see /lua/src/ldump.c#DumpByte
 */
public class LuByte extends LuacOutComponent {

    private int value;

    public int getValue() {
        return value;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        value = reader.readUnsignedByte();
        super.setDesc(Integer.toString(value));

        // todo
        if (super.getName() != null) {
            switch (super.getName()) {
                case "sizeof(int)":
                    reader.setCIntSize(value);
                    break;
                case "sizeof(size_t)":
                    reader.setSizetSize(value);
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
