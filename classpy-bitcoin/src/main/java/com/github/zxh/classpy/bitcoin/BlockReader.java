package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.common.BytesReader;

import java.nio.ByteOrder;

// https://en.wikipedia.org/wiki/LEB128
public class BlockReader extends BytesReader {

    public BlockReader(byte[] data) {
        super(data, ByteOrder.LITTLE_ENDIAN); // ?
    }

    public long readVarInt() {
        int b = readByte() & 0xFF;
        if (b < 0xFD) {
            return b;
        } if (b == 0xFD) {
            return readFixedU16();
        } if (b == 0xFE) {
            return readFixedU32();
        } else {
            return readFixedI64();
        }
    }

}
