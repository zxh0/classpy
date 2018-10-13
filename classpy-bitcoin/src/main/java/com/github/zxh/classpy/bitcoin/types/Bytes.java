package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockComponent;
import com.github.zxh.classpy.bitcoin.BlockReader;

public class Bytes extends BlockComponent {

    private final int n;
    private byte[] bytes;

    public Bytes(int n) {
        this.n = n;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    protected void readContent(BlockReader reader) {
        bytes = reader.readBytes(n);
    }

}
