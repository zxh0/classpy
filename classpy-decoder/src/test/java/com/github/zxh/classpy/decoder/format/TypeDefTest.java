package com.github.zxh.classpy.decoder.format;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class TypeDefTest {

    @Test
    public void names() {
        testFormatEx("'name' not found: {}", "{}");
        testFormatEx("'name' is not String or Array: {}", """
                {"name": {}}
                """);
        testFormatEx("not a String: {}", """
                {"name": [{}]}
                """);

        assertEquals(Collections.singletonList("a"),
                parseTypeDef("""
                {"name": "a", "format": []}
                """).getNames());

        assertEquals(Arrays.asList("a", "b"),
                parseTypeDef("""
                {"name": ["a", "b"], "format": []}
                """).getNames());
    }

    @Test
    public void fields() {
        testFormatEx("'format' not found", """
                {"name": "a"}
                """);
        testFormatEx("'format' is not Array", """
                {"name": "a", "format": "b"}
                """);
        testFormatEx("not an Object: 123", """
                {"name": "a", "format": [123]}
                """);
    }

    private static void testFormatEx(String errMsg, String json) {
        var ex = assertThrows(FormatException.class,
                () -> parseTypeDef(json));
        assertTrue(ex.getMessage().contains(errMsg), ex.getMessage());
    }

    private static TypeDef parseTypeDef(String json) {
        return new TypeDef(new Gson().fromJson(json, JsonObject.class));
    }

}
