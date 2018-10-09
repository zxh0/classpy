package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.wasm.WasmBinComponent;

public class TableType extends WasmBinComponent {

    {
        _byte("elemtype", (byte) 0x70);
        add("limits", new Limits());
        setName("table");
    }

}
