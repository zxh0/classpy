package com.github.zxh.classpy.decoder.format;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldDefTest {

    @Test
    public void formatEx() {
        testFormatEx("'name' not found: {}", "{}");
        testFormatEx("'type' not found", """
                {"name": "a"}
                """);
    }

    private static void testFormatEx(String errMsg, String json) {
        var ex = assertThrows(FormatException.class,
                () -> parseFieldDef(json));
        assertTrue(ex.getMessage().contains(errMsg), ex.getMessage());
    }

    private static FieldDef parseFieldDef(String json) {
        return new FieldDef(new Gson().fromJson(json, JsonObject.class));
    }

}
