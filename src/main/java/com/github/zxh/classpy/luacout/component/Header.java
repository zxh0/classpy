package com.github.zxh.classpy.luacout.component;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.datatype.LuaInt;

/**
 * Header.
 */
public class Header extends LuacOutComponent {

    {
        literal("LUA_SIGNATURE",    4);
        lu_byte("LUA_VERSION"        );
        lu_byte("LUAC_FORMAT"        );
        literal("LUAC_DATA",        6);
        lu_byte("sizeof(int)"        );
        lu_byte("sizeof(size_t)"     );
        lu_byte("sizeof(Instruction)");
        lu_byte("sizeof(lua_Integer)");
        lu_byte("sizeof(lua_Number)" );
        lua_int("LUAC_INT"           );
        lua_num("LUAC_NUM"           );
    }

    @Override
    protected void afterRead() {
        LuaInt luacInt = (LuaInt) super.get("LUAC_INT");
        luacInt.setDesc("0x" + Long.toHexString(luacInt.getValue()));
    }

}
