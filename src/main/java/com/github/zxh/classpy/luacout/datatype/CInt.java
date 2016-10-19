package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * c int.
 *
 * @see /lua/src/ldump.c#DumpInt()
 */
public class CInt extends LuacOutComponent {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        value = reader.readCInt();
        setDesc(Long.toString(value));
    }

}
