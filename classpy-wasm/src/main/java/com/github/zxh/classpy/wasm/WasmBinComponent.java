package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.wasm.instructions.Expr;
import com.github.zxh.classpy.wasm.types.ValType;
import com.github.zxh.classpy.wasm.values.*;
import com.github.zxh.classpy.wasm.values.Byte;

import java.util.function.Supplier;

public class WasmBinComponent extends FileComponent {

    public final void read(WasmBinReader reader) {
        try {
            int offset = reader.getPosition();
            readContent(reader);
            int length = reader.getPosition() - offset;
            super.setOffset(offset);
            super.setLength(length);
        } catch (Exception e) {
            System.out.println("error parsing: " + getClass());
            throw e;
        }
    }

    protected void readContent(WasmBinReader reader) {
        for (FileComponent fc : getComponents()) {
            ((WasmBinComponent) fc).read(reader);
        }
    }

    protected void postRead(WasmBinFile wasm) {

    }

    protected int readU32(WasmBinReader reader, String name) {
        U32 u32 = new U32();
        add(name, u32);
        u32.read(reader);
        return u32.getIntValue();
    }

    protected int readIndex(WasmBinReader reader, String name) {
        Index idx = new Index();
        add(name, idx);
        idx.read(reader);
        return idx.getIntValue();
    }

    protected int readByte(WasmBinReader reader, String name) {
        Byte b = new Byte();
        add(name, b);
        b.read(reader);
        return b.getValue();
    }

    protected int readByte(WasmBinReader reader, String name, byte... expectedValues) {
        Byte b = new Byte(expectedValues);
        add(name, b);
        b.read(reader);
        return b.getValue();
    }

    protected byte[] readBytes(WasmBinReader reader, String name, int n) {
        Bytes bytes = new Bytes(n);
        add(name, bytes);
        bytes.read(reader);
        return bytes.getBytes();
    }

    protected String readName(WasmBinReader reader, String name) {
        Name nm = new Name();
        add(name, nm);
        nm.read(reader);
        return nm.getDesc();
    }

    protected <T extends WasmBinComponent> T read(WasmBinReader reader,
                                                  String name, T c) {
        add(name, c);
        c.read(reader);
        return c;
    }

    protected void readVector(WasmBinReader reader, String name,
                              Supplier<? extends WasmBinComponent> supplier) {
        Vector vec = new Vector(supplier);
        add(name, vec);
        vec.read(reader);
    }

    protected void _byte(String name, byte... expectedValues) {
        add(name, new Byte(expectedValues));
    }

    protected void u32(String name) {
        add(name, new U32());
    }

    protected void idx(String name) {
        add(name, new Index());
    }

    protected void valType(String name) {
        add(name, new ValType());
    }

    protected void expr(String name) {
        add(name, new Expr());
    }

    protected void vector(String name,
                          Supplier<? extends WasmBinComponent> supplier) {
        add(name, new Vector(supplier));
    }

}
