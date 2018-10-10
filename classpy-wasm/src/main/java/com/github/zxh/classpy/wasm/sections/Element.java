package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.values.U32;

public class Element extends WasmBinComponent {

    {
        idx("tableidx");
        expr("offset");
        vector("init", U32::new);
        setName("element");
    }

}
