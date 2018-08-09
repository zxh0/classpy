package com.github.zxh.classpy.lua.binarychunk.datatype;

import com.github.zxh.classpy.lua.binarychunk.BinaryChunkComponent;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;

/**
 * byte array.
 */
public class Bytes extends BinaryChunkComponent {

    private final int n;
    private byte[] bytes;

    public Bytes(int n) {
        this.n = n;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        bytes = reader.readBytes(n);
    }

}
