package com.github.zxh.classpy.lua54.vm;

import com.github.zxh.classpy.common.ParseException;

/**
 * Lua types.
 * @see /lua/src/lua.h
 * @see /lua/src/lobject.h
 */
public enum LuaType {

    /* basic types */
    LUA_TNONE         (-1),
    LUA_TNIL          (0),
    LUA_TBOOLEAN      (1),
    LUA_TLIGHTUSERDATA(2),
    LUA_TNUMBER       (3),
    LUA_TSTRING       (4),
    LUA_TTABLE        (5),
    LUA_TFUNCTION     (6),
    LUA_TUSERDATA     (7),
    LUA_TTHREAD       (8),

    // Variant tags
    LUA_VNIL(0),
    LUA_VFALSE (LUA_TBOOLEAN.type | (0 << 4)), // false
    LUA_VTRUE  (LUA_TBOOLEAN.type | (1 << 4)), // true
    LUA_VNUMINT(LUA_TNUMBER.type  | (0 << 4)), // integer numbers
    LUA_VNUMFLT(LUA_TNUMBER.type  | (1 << 4)), // float numbers
    LUA_VSHRSTR(LUA_TSTRING.type  | (0 << 4)), // short strings
    LUA_VLNGSTR(LUA_TSTRING.type  | (1 << 4)), // long strings

    ;

    public final int type;

    private LuaType(int type) {
        this.type = type;
    }

    public static LuaType valueOf(int type) {
        for (LuaType lt : values()) {
            if (lt.type == type) {
                return lt;
            }
        }
        throw new ParseException("Unknown lua type: " + type);
    }

}
