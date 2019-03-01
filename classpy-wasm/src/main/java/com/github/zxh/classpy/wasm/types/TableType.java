package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.wasm.WasmBinPart;

public class TableType extends WasmBinPart {

    {
        _byte("elemtype", (byte) 0x70);
        add("limits", new Limits());
        setName("table");
    }

}
