package com.github.zxh.classpy.luacout.lvm;

import com.github.zxh.classpy.common.BytesParseException;

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

    /* Variant tags for numbers */
    LUA_TNUMFLT(LUA_TNUMBER.type | (0 << 4)),  /* float numbers */
    LUA_TNUMINT(LUA_TNUMBER.type | (1 << 4)),  /* integer numbers */

    /* Variant tags for strings */
    LUA_TSHRSTR(LUA_TSTRING.type | (0 << 4)),  /* short strings */
    LUA_TLNGSTR(LUA_TSTRING.type | (1 << 4)),  /* long strings */

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
        throw new BytesParseException("Unknown lua type: " + type);
    }

}
