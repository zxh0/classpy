package com.github.zxh.classpy.lua53.binarychunk;

import com.github.zxh.classpy.common.BytesReader;

import java.nio.ByteOrder;

public class BinaryChunkReader extends BytesReader {

    private int sizetSize = 4;
    private int cIntSize = 4;
    private int luaIntSize = 8;
    private int luaNumSize = 8;

    public BinaryChunkReader(byte[] data) {
        super(data, ByteOrder.LITTLE_ENDIAN);
    }

    // setters
    public void setSizetSize(int sizetSize) {this.sizetSize = sizetSize;}
    public void setCIntSize(int cIntSize) {this.cIntSize = cIntSize;}
    public void setLuaIntSize(int luaIntSize) {this.luaIntSize = luaIntSize;}
    public void setLuaNumSize(int luaNumSize) {this.luaNumSize = luaNumSize;}

    public long readSizet() {
        return sizetSize == 8 ? super.readFixedI64() : super.readFixedU32();
    }

    public long readCInt() {
        return cIntSize == 8 ? super.readFixedI64() : super.readFixedI32();
    }

    public long readLuaInt() {
        return luaIntSize == 8 ? super.readFixedI64() : super.readFixedI32();
    }

    public double readLuaNum() {
        return luaNumSize == 8 ? super.readF64(): super.readF32();
    }

}
