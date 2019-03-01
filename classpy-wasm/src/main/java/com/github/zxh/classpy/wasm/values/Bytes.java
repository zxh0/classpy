package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Bytes extends WasmBinPart {

    private final int n;
    private byte[] bytes;

    public Bytes(int n) {
        this.n = n;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        bytes = reader.readBytes(n);
    }

}
