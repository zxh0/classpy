package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinFile;

import java.util.stream.Collectors;

public class FuncType extends WasmBinComponent {

    {
        _byte(null, (byte) 0x60);
        vector("parameters", ValType::new);
        vector("results", ValType::new);
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        String params = get("parameters").getComponents().stream()
                .skip(1)
                .map(FilePart::getDesc)
                .collect(Collectors.joining(",", "(", ")"));
        String results = get("results").getComponents().stream()
                .skip(1)
                .map(FilePart::getDesc)
                .collect(Collectors.joining(",", "(", ")"));
        setDesc(params + "->" + results);
    }

}
