package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * Lua number.
 * typedef LUA_NUMBER lua_Number;
 * #define LUA_NUMBER	float
 * #define LUA_NUMBER	long double
 * #define LUA_NUMBER	double
 *
 * @see /lua/src/lua.h
 * @see /lua/src/luaconf.h
 * @see /lua/src/ldump.c#DumpNumber()
 */
public class LuaNum extends LuacOutComponent {

    private double value;

    @Override
    protected void readContent(LuacOutReader reader) {
        value = reader.readLuaNum();
        setDesc(Double.toString(value));
    }

}
