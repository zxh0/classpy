package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeDef {

    @Getter
    private final List<String> names;
    @Getter
    private final List<FieldDef> fields;

    public TypeDef(JsonObject tdJson) {
        this.names = getNames(tdJson);
        if (tdJson.get("type") != null) {
            // TODO
            this.fields = Collections.emptyList();
        } else {
            this.fields = Collections.unmodifiableList(getFields(tdJson));
        }
    }

    private static List<String> getNames(JsonObject tdJson) {
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

    private static List<FieldDef> getFields(JsonObject tdJson) {
        JsonArray format = JsonHelper.getArray(tdJson, "format");
        List<FieldDef> fds = new ArrayList<>(format.getAsJsonArray().size());
        for (JsonElement e : format) {
            fds.add(new FieldDef(JsonHelper.toObject(e)));
        }
        return fds;
    }

}
