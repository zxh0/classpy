package com.github.zxh.classpy.wasm;

import org.junit.Assert;
import org.junit.Test;

public class LEB128Test {

    @Test
    public void readU32() {
        Assert.assertEquals(624485,
                new WasmBinReader(new byte[] {(byte) 0xE5, (byte) 0x8E, 0x26}).readU32());
    }

    @Test
    public void readS32() {
        Assert.assertEquals(-624485,
                new WasmBinReader(new byte[] {(byte) 0x9B, (byte) 0xF1, 0x59}).readS32());
    }

}
