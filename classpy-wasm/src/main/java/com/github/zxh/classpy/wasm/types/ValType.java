package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class ValType extends WasmBinComponent {

    @Override
    protected void readContent(WasmBinReader reader) {
        byte b = reader.readByte();
        switch (b) {
            case 0x7F: setDesc("i32"); break;
            case 0x7E: setDesc("i64"); break;
            case 0x7D: setDesc("f32"); break;
            case 0x7C: setDesc("f64"); break;
            default: throw new ParseException(
                    String.format("Invalid value type: 0x%02X", b));
        }
    }

}
