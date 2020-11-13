package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.bitcoin.types.*;
import com.github.zxh.classpy.common.ReadableFilePart;

import java.util.function.Supplier;

public class BlockPart extends ReadableFilePart<BlockReader> {

    protected <T extends BlockPart> T read(BlockReader reader,
                                           String name, T c) {
        add(name, c);
        c.read(reader);
        return c;
    }

    protected long readVarInt(BlockReader reader, String name) {
        VarInt varInt = new VarInt();
        add(name, varInt);
        varInt.read(reader);
        return varInt.getValue();
    }

    protected void uint32(String name) {
        add(name, new UInt32());
    }

    protected void uint64(String name) {
        add(name, new UInt64());
    }

    protected void hash(String name) {
        add(name, new Hash());
    }

    protected void bytes(String name, int n) {
        add(name, new Bytes(n));
    }

    protected void script(String name) {
        add(name, new Script());
    }

    protected void table(String name,
                         Supplier<? extends BlockPart> supplier) {
        add(name, new Table(supplier));
    }

}
