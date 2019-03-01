package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinFile;
import com.github.zxh.classpy.wasm.instructions.Expr;
import com.github.zxh.classpy.wasm.types.GlobalType;
import com.github.zxh.classpy.wasm.values.Byte;

public class Global extends WasmBinPart {

    {
        add("type", new GlobalType());
        add("init", new Expr());
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        GlobalType gt = (GlobalType) get("type");
        Byte valType = (Byte) gt.getParts().get(1);
        String mut = gt.getParts().get(0).getDesc();
        String desc = mut + (valType.getValue() == 0 ? "/const" : "/var");
        setDesc(desc);
    }

}
