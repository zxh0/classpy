package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * c size_t.
 */
public class CSizet extends LuacOutComponent {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        value = reader.readSizet();
        setDesc(Long.toString(value));
    }

}
