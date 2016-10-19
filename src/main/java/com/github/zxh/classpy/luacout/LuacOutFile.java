package com.github.zxh.classpy.luacout;

import com.github.zxh.classpy.luacout.component.Function;
import com.github.zxh.classpy.luacout.component.Header;
import com.github.zxh.classpy.luacout.datatype.LuByte;

/**
 * @see /lua/src/ldump.c#luaU_dump().
 */
public class LuacOutFile extends LuacOutComponent {

    {
        add("header",        new Header());
        add("size_upvalues", new LuByte());
        add("main",        new Function());
    }

}
