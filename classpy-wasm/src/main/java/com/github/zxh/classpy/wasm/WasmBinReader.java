package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.common.BytesReader;

import java.nio.ByteOrder;

// https://en.wikipedia.org/wiki/LEB128
public class WasmBinReader extends BytesReader {

    public WasmBinReader(byte[] data) {
        super(data, ByteOrder.LITTLE_ENDIAN);
    }

    public long readU32() {
        return readUnsignedLEB128(32);
    }

    public long readU64() {
        return readUnsignedLEB128(64);
    }

    public long readS32() {
        return readSignedLEB128(32);
    }

    public long readS64() {
        return readSignedLEB128(64);
    }

    private long readUnsignedLEB128(int nBits) {
        long result = 0;
        for (int shift = 0; shift < nBits; shift += 7) {
            int b = readByte();
            result |= ((b & 0b0111_1111) << shift);
            if ((b & 0b1000_0000) == 0) {
                return result;
            }
        }

        throw new RuntimeException("can not decode unsigned LEB128");
    }

    private long readSignedLEB128(int size) {
        long result = 0;
        int shift = 0;
        //size = number of bits in signed integer;
        byte b;
        do{
            b = readByte();
            result |= ((b & 0b0111_1111) << shift);
            shift += 7;
        } while ((b & 0b1000_0000) != 0);

        /* sign bit of byte is second high order bit (0x40) */
        if ((shift < size) && ((b & 0b0100_0000) != 0)) {
            /* sign extend */
            result |= (~0 << shift);
        }

        return result;
    }

}
