package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.wasm.WasmBinComponent;

public class GlobalType extends WasmBinComponent {

    {
        valType("valtype");
        _byte("mut", (byte) 0x00, (byte) 0x01);
    }

}
