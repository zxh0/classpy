package com.github.zxh.classpy.wasm;

import java.util.function.Supplier;

public class Vector extends WasmBinPart {

    private Supplier<? extends WasmBinPart> supplier;

    public Vector(Supplier<? extends WasmBinPart> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        int length = readU32(reader, "length");
        for (int i = 0; i < length; i++) {
            WasmBinPart element = supplier.get();
            add(null, element);
            element.read(reader);
        }
    }

}
