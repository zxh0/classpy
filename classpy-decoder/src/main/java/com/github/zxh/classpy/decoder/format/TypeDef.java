package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.*;

public abstract class TypeDef {

    @Getter
    private final List<String> names;

    public TypeDef(JsonObject tdJson) {
        names = parseNames(tdJson);
    }

    private static List<String> parseNames(JsonObject tdJson) {
        JsonElement name = JsonHelper.getField(tdJson, "name");
        if (name.isJsonPrimitive()) {
            return Collections.singletonList(name.getAsString());
        }
        if (name.isJsonArray()) {
            List<String> names = new ArrayList<>(name.getAsJsonArray().size());
            for (JsonElement e : name.getAsJsonArray()) {
                names.add(JsonHelper.toString(e));
            }
            return names;
        }
        throw new FormatException("'name' is not String or Array", name);
    }

    public static TypeDef parse(JsonObject tdJson) {
        if (tdJson.has("tagged")) {
            return new TaggedTypeDef(tdJson);
        } else if (tdJson.has("named")) {
            return new NamedTypeDef(tdJson);
        } else {
            return new StructTypeDef(tdJson);
        }
    }

}
