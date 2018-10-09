package com.github.zxh.classpy.wasm;

import java.util.function.Supplier;

public class Vector extends WasmBinComponent {

    private Supplier<? extends WasmBinComponent> supplier;

    public Vector(Supplier<? extends WasmBinComponent> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        int length = readU32(reader, "length");
        for (int i = 0; i < length; i++) {
            WasmBinComponent element = supplier.get();
            add(null, element);
            element.read(reader);
        }
    }

}
