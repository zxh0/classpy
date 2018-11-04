package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinFile;
import com.github.zxh.classpy.wasm.instructions.Expr;
import com.github.zxh.classpy.wasm.types.GlobalType;
import com.github.zxh.classpy.wasm.values.Byte;

public class Global extends WasmBinComponent {

    {
        add("type", new GlobalType());
        add("init", new Expr());
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        GlobalType gt = (GlobalType) get("type");
        Byte valType = (Byte) gt.getComponents().get(1);
        String mut = gt.getComponents().get(0).getDesc();
        String desc = mut + (valType.getValue() == 0 ? "/const" : "/var");
        setDesc(desc);
    }

}
