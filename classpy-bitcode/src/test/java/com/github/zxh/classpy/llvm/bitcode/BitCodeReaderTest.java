package com.github.zxh.classpy.llvm.bitcode;

import org.junit.Test;
import static org.junit.Assert.*;

public class BitCodeReaderTest {

    @Test
    public void readFixed() {
        var r = new BitCodeReader(new byte[] {
                0b0001_0001, 0b0001_0010, 0b0001_0011, 0b0001_0100,
                0b0010_0101, 0b0010_0110, 0b0010_0111, 0b0010_1000,
                0b0100_1001, 0b0100_1010, 0b0100_1011, 0b0100_1100});

        assertEquals(0b0001, r.readFixed(4));
        assertEquals(0b0001, r.readFixed(4));
        assertEquals(0b0010, r.readFixed(4));
        assertEquals(0b0001, r.readFixed(4));
        assertEquals(0b0011, r.readFixed(4));
        assertEquals(0b0001, r.readFixed(4));
        assertEquals(0b0100, r.readFixed(4));
        assertEquals(0b0001, r.readFixed(4));

        assertEquals(0b0101, r.readFixed(4));
        assertEquals(0b0010, r.readFixed(4));
        assertEquals(0b0110, r.readFixed(4));
        assertEquals(0b0010, r.readFixed(4));
        assertEquals(0b0111, r.readFixed(4));
        assertEquals(0b0010, r.readFixed(4));
        assertEquals(0b1000, r.readFixed(4));
        assertEquals(0b0010, r.readFixed(4));

        assertEquals(0b1001, r.readFixed(4));
        assertEquals(0b0100, r.readFixed(4));
        assertEquals(0b1010, r.readFixed(4));
        assertEquals(0b0100, r.readFixed(4));
        assertEquals(0b1011, r.readFixed(4));
        assertEquals(0b0100, r.readFixed(4));
        assertEquals(0b1100, r.readFixed(4));
        assertEquals(0b0100, r.readFixed(4));
    }

    @Test
    public void readVBR() {
        var r = new BitCodeReader(new byte[] {0b0011_1011, 0, 0, 0});
        assertEquals(27, r.readVBR(4));
    }

}
