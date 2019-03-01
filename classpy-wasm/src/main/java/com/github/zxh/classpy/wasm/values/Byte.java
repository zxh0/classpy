package com.github.zxh.classpy.wasm.values;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Byte extends WasmBinPart {

    private final byte[] expectedValues;
    private byte value;

    public Byte() {
        this.expectedValues = null;
    }

    public Byte(byte... expectedValues) {
        this.expectedValues = expectedValues;
    }

    public int getValue() {
        return value & 0xFF;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        value = reader.readByte();
        setDesc(String.format("0x%02X", value));
        checkValue();
    }

    private void checkValue() {
        if (expectedValues == null || expectedValues.length == 0) {
            return;
        }

        for (byte expectedValue : expectedValues) {
            if (expectedValue == value) {
                return;
            }
        }

        throw new ParseException(String.format(
                "Unexpected byte: 0x%02X", value));
    }

}
