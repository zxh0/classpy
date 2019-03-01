package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinFile;
import com.github.zxh.classpy.wasm.values.Byte;

public class GlobalType extends WasmBinPart {

    {
        valType("valtype");
        _byte("mut", (byte) 0x00, (byte) 0x01);
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        Byte mut = (Byte) get("mut");
        mut.setDesc(mut.getValue() == 0 ? "const" : "var");
    }

}
