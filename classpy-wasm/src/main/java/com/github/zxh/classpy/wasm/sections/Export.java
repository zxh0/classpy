package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Export extends WasmBinComponent {

    {
        name("name");
        add("desc", new Desc());
    }

    @Override
    protected void postRead() {
        setDesc(get("name").getDesc());
    }

    private static class Desc extends WasmBinComponent {

        @Override
        protected void readContent(WasmBinReader reader) {
            int b = readByte(reader, null);
            switch (b) {
                case 0x00: readU32(reader, "func");   break; // funcidx
                case 0x01: readU32(reader, "table");  break; // tableidx
                case 0x02: readU32(reader, "mem");    break; // memidx
                case 0x03: readU32(reader, "global"); break; // globalidx
                default: throw new ParseException("Invalid export desc: " + b);
            }
        }

    }

}
