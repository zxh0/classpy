package com.github.zxh.classpy.decoder.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListWithoutLenTypeDefTest {

    @Test
    public void parse() {
        var type = new ListWithoutLenTypeDef("[foo]bar");
        assertEquals("foo", type.getLenFieldName());
        assertEquals("bar", type.getElemTypeName());
        assertFalse(type.hasMinusOne());

        type = new ListWithoutLenTypeDef("[bar-1]foo");
        assertEquals("bar", type.getLenFieldName());
        assertEquals("foo", type.getElemTypeName());
        assertTrue(type.hasMinusOne());
    }

}
