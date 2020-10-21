package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaggedTypeDef extends TypeDef {

    @Getter
    private final String tagType;
    @Getter
    private final Map<Integer, String> tags;

    public TaggedTypeDef(JsonObject tdJson) {
        super(tdJson);
        tagType = JsonHelper.getString(tdJson, "tagType");
        tags = Collections.unmodifiableMap(parseTags(tdJson));
    }

    private static Map<Integer, String> parseTags(JsonObject tdJson) {
        JsonArray tags = JsonHelper.getArray(tdJson, "tags");
        Map<Integer, String> tds = new HashMap<>();
        for (JsonElement tag : tags) {
            JsonObject tagObj = JsonHelper.toObject(tag);
            tds.put(JsonHelper.getInteger(tagObj, "tag"),
                    JsonHelper.getString(tagObj, "type"));
        }
        return tds;
    }

}
