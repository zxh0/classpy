package com.github.zxh.classpy.wasm.instructions;

import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Expr extends WasmBinPart {

    @Override
    protected void readContent(WasmBinReader reader) {
        while (reader.remaining() > 0) {
            Instr instr = read(reader, null, new Instr());
            if (instr.getOpcode() == 0x0B) { // end
                break;
            }
        }
    }

}
