package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * byte array.
 */
public class Bytes extends LuacOutComponent {

    private final int n;
    private byte[] bytes;

    public Bytes(int n) {
        this.n = n;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        bytes = reader.readBytes(n);
    }

}
