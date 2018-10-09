package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Limits extends WasmBinComponent {

    @Override
    protected void readContent(WasmBinReader reader) {
        int flag = readByte(reader, "flag", (byte) 0, (byte) 1);

        int min = readU32(reader, "min");
        setDesc("{" + min + ", }");

        if (flag == 1) {
            int max = readU32(reader, "max");
            setDesc("{" + min + ", " + max + "}");
        }
    }

}
