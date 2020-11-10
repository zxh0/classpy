package com.github.zxh.classpy.lua54.binarychunk;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.ParseException;

import java.nio.ByteOrder;

public class BinaryChunkReader extends BytesReader {

    private int instrSize = 4;
    private int luaIntSize = 8;
    private int luaNumSize = 8;

    public BinaryChunkReader(byte[] data) {
        super(data, ByteOrder.LITTLE_ENDIAN);
    }

    // setters
    public void setInstrSize(int sizetSize) {this.instrSize = sizetSize;}
    public void setLuaIntSize(int luaIntSize) {this.luaIntSize = luaIntSize;}
    public void setLuaNumSize(int luaNumSize) {this.luaNumSize = luaNumSize;}

    public long readLuaInt() {
        return luaIntSize == 8 ? super.readLong() : super.readInt();
    }

    public double readLuaNum() {
        return luaNumSize == 8 ? super.readDouble(): super.readFloat();
    }

    // lua5.4.1/lundump.c#loadInt()
    public int readVarInt() {
        return (int) readUnsigned(Integer.MAX_VALUE);
    }

    // lua5.4.1/lundump.c#loadUnsigned()
    private long readUnsigned (long limit) {
        long x = 0;
        byte b;
        limit >>= 7;
        do {
            b = readByte();
            if (x >= limit)
                throw new ParseException("integer overflow");
            x = (x << 7) | (b & 0x7f);
        } while ((b & 0x80) == 0);
        return x;
    }

}
