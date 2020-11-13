package com.github.zxh.classpy.lua54.binarychunk;

import com.github.zxh.classpy.common.ReadableFilePart;
import com.github.zxh.classpy.lua54.binarychunk.datatype.*;

import java.util.function.Supplier;

/**
 * Component of lua binary chunk file.
 */
public class BinaryChunkPart extends ReadableFilePart<BinaryChunkReader> {

    protected void postRead() {

    }

    public void lu_byte(String name) {
        super.add(name, new LuByte());
    }

    public void lua_int(String name) {
        super.add(name, new LuaInt());
    }

    public void varInt(String name) {
        super.add(name, new VarInt());
    }

    public void lua_num(String name) {
        super.add(name, new LuaNum());
    }

    public void str(String name) {
        super.add(name, new LuaStr());
    }

    public void bytes(String name, int n) {
        super.add(name, new Bytes(n));
    }

    public void vector(String name, Supplier<BinaryChunkPart> partSupplier) {
        super.add(name, new Vec(partSupplier));
    }

}
