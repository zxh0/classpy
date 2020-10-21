package com.github.zxh.classpy.decoder.format;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileFormatDefTest {

    @Test
    public void name() {
        testFormatEx("'name' not found", "{}");
        testFormatEx("'name' is not String", """
                {"name": []}
                """);
        testFormatEx("'name' is not String", """
                {"name": {}}
                """);
    }

    @Test
    public void version() {
        testFormatEx("'version' not found",
                """
                {"name": "a"}
                """);
    }

    @Test
    public void type() {
        testFormatEx("'type' not found",
                """
                {"name": "a", "version": 1}
                """);
    }

    @Test
    public void byteOrder() {
        testFormatEx("'endianness' not found",
                """
                {"name": "a", "version": 1, "type": "b"}
                """);
        testFormatEx("invalid endianness: x", """
                {"name": "a", "version": 1, "type": "b", "endianness": "x"}
                """);
    }

    @Test
    public void types() {
        testFormatEx("'types' not found", """
                {"name": "a", "version": 1, "type": "b", "endianness": "little-endian", "builtin": []}
                """);
        testFormatEx("'types' is not Array", """
                {"name": "a", "version": 1, "type": "b", "endianness": "big-endian", "builtin": [], "types": "foo"}
                """);
        testFormatEx("not an Object: 123", """
                {"name": "a", "version": 1, "type": "b", "endianness": "big-endian", "builtin": [], "types": [123]}
                """);
        testFormatEx("duplicated type names: b", """
                {"name": "a", "version": 1, "type": "b", "endianness": "big-endian", "builtin": [], "types": [
                  {"name": "b", "format": []},
                  {"name": "b", "format": []}
                ]}
                """);
    }

    @Test
    public void getType() {
        FileFormatDef ffDef = parseFileFormatDef("""
                {
                  "name": "a", "version": 1, "type": "b", "endianness": "big-endian", "builtin": [],
                  "types": [
                    {"name": "b", "format": []}
                  ]
                }
                """);
        var ex = assertThrows(FormatException.class,
                () -> ffDef.getTypeDef("t"));
        assertEquals("type not found: t", ex.getMessage());
    }

    private static void testFormatEx(String errMsg, String json) {
        var ex = assertThrows(FormatException.class,
                () -> parseFileFormatDef(json));
        assertTrue(ex.getMessage().contains(errMsg), ex.getMessage());
    }

    private static FileFormatDef parseFileFormatDef(String json) {
        return new FileFormatDef(new Gson().fromJson(json, JsonObject.class));
    }

}
