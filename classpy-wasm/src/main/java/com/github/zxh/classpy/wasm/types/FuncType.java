package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.wasm.WasmBinComponent;

import java.util.stream.Collectors;

public class FuncType extends WasmBinComponent {

    {
        _byte(null, (byte) 0x60);
        vector("parameters", ValType::new);
        vector("results", ValType::new);
    }

    @Override
    protected void postRead() {
        String params = get("parameters").getComponents().stream()
                .skip(1)
                .map(FileComponent::getDesc)
                .collect(Collectors.joining(",", "(", ")"));
        String results = get("results").getComponents().stream()
                .skip(1)
                .map(FileComponent::getDesc)
                .collect(Collectors.joining(",", "(", ")"));
        setDesc(params + "->" + results);
    }

}
