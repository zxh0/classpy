package com.github.zxh.classpy.lua53.binarychunk;

import com.github.zxh.classpy.common.ReadableFilePart;
import com.github.zxh.classpy.lua53.binarychunk.datatype.*;

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

    public void cint(String name) {
        super.add(name, new CInt());
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

    public void table(String name, Supplier<BinaryChunkPart> partSupplier) {
        super.add(name, new Table(partSupplier));
    }

}
