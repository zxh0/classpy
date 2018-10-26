package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.wasm.WasmBinFile;

public class Index extends U32 {

    @Override
    protected void postRead(WasmBinFile wasm) {
        setDesc("#" + value);
    }

}
