package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class S32 extends WasmBinComponent {

    private long value;

    public long getValue() {
        return value;
    }

    public int getIntValue() {
        return (int) value;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        value = reader.readS32();
        setDesc(Long.toString(value));
    }

}
