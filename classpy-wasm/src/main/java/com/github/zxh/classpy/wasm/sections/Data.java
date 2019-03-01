package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Data extends WasmBinPart {

    {
        idx("memidx");
        expr("offset");
        add("init", new Init());
        setName("data");
    }

    private static class Init extends WasmBinPart {

        @Override
        protected void readContent(WasmBinReader reader) {
            int length = readU32(reader, "length");
            readBytes(reader, "bytes", length);
        }

    }

}
