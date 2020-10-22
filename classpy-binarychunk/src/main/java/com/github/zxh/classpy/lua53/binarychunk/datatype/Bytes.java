package com.github.zxh.classpy.lua53.binarychunk.datatype;

import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua53.binarychunk.BinaryChunkReader;

/**
 * byte array.
 */
public class Bytes extends BinaryChunkPart {

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
