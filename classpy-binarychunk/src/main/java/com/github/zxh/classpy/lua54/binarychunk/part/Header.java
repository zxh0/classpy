package com.github.zxh.classpy.lua54.binarychunk.part;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.datatype.Bytes;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuByte;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuaInt;

import java.util.Arrays;

// lua5.4.1/lundump.c#checkHeader()
public class Header extends BinaryChunkPart {

    private final byte[] _luaSig = {0x1b, 'L', 'u', 'a'};
    private final byte[] _luacData = {0x19, (byte) 0x93, '\r', '\n', 0x1a, '\n'};

    {
        bytes  ("LUA_SIGNATURE",    4);
        lu_byte("LUAC_VERSION"       );
        lu_byte("LUAC_FORMAT"        );
        bytes  ("LUAC_DATA",        6);
        lu_byte("sizeof(Instruction)");
        lu_byte("sizeof(lua_Integer)");
        lu_byte("sizeof(lua_Number)" );
        lua_int("LUAC_INT"           );
        lua_num("LUAC_NUM"           );
    }

    @Override
    protected void postRead() {
        checkSignature();
        checkLuacData();
        LuByte luaVersion = (LuByte) super.get("LUAC_VERSION");
        luaVersion.setDesc("0x" + Integer.toHexString(luaVersion.getValue()));
        LuaInt luacInt = (LuaInt) super.get("LUAC_INT");
        luacInt.setDesc("0x" + Long.toHexString(luacInt.getValue()));
    }

    private void checkSignature() {
        Bytes sig = (Bytes) super.get("LUA_SIGNATURE");
        if (!Arrays.equals(sig.getBytes(), _luaSig)) {
            throw new ParseException("not a precompiled chunk!");
        } else {
            sig.setDesc("\"\\x1bLua\"");
        }
    }

    private void checkLuacData() {
        Bytes luacData = (Bytes) super.get("LUAC_DATA");
        if (!Arrays.equals(luacData.getBytes(), _luacData)) {
            throw new ParseException("corrupted!");
        } else {
            luacData.setDesc("\"\\x19\\x93\\r\\n\\x1a\\n\"");
        }
    }

}
