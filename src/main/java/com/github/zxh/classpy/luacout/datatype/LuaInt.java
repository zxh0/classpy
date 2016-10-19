package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * typedef LUA_INTEGER lua_Integer;
 * #define LUA_INTEGER		int
 * #define LUA_INTEGER		long
 * #define LUA_INTEGER		long long
 * #define LUA_INTEGER		__int64
 *
 * @see /lua/src/lua.h
 * @see /lua/src/luaconf.h
 * @see /lua/src/ldump.c#DumpInteger()
 */
public class LuaInt extends LuacOutComponent {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        value = reader.readLuaInt();
        setDesc(Long.toString(value));
    }

}
