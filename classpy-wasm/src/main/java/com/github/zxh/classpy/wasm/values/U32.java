package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class U32 extends WasmBinComponent {

    protected long value;

    public long getValue() {
        return value;
    }

    public int getIntValue() {
        return (int) value;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        value = reader.readU32();
        setDesc(Long.toString(value));
    }

}
