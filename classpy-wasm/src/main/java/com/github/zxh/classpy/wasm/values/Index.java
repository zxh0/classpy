package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.wasm.WasmBinReader;

public class Index extends U32 {

    @Override
    protected void readContent(WasmBinReader reader) {
        value = reader.readU32();
        setDesc("#" + value);
    }

}
