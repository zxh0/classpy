package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StructTypeDef extends TypeDef {

    @Getter
    private final List<FieldDef> fields;

    public StructTypeDef(JsonObject tdJson) {
        super(tdJson);
        fields = Collections.unmodifiableList(parseFields(tdJson));
    }

    private static List<FieldDef> parseFields(JsonObject tdJson) {
        JsonArray fields = JsonHelper.getArray(tdJson, "format");
        List<FieldDef> fds = new ArrayList<>(fields.getAsJsonArray().size());
        for (JsonElement field : fields) {
            fds.add(new FieldDef(JsonHelper.toObject(field)));
        }
        return fds;
    }

}
