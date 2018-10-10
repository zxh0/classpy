package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

import java.nio.charset.StandardCharsets;

public class Name extends WasmBinComponent {

    @Override
    protected void readContent(WasmBinReader reader) {
        int length = readU32(reader, "length");
        byte[] bytes = readBytes(reader, "bytes", length);
        setDesc(new String(bytes, StandardCharsets.UTF_8));
    }

}
