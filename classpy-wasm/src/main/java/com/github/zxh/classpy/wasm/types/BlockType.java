package com.github.zxh.classpy.wasm.types;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class BlockType extends WasmBinComponent {

    @Override
    protected void readContent(WasmBinReader reader) {
        byte valType = reader.readByte();
        switch (valType) {
            case 0x40: setDesc("");    break;
            case 0x7F: setDesc("i32"); break;
            case 0x7E: setDesc("i64"); break;
            case 0x7D: setDesc("f32"); break;
            case 0x7C: setDesc("f64"); break;
            default: throw new ParseException(
                    String.format("Invalid block type: 0x%02X", valType));
        }
    }

}
