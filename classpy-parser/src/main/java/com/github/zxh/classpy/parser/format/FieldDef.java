package com.github.zxh.classpy.parser.format;

import com.google.gson.JsonObject;
import lombok.Getter;

public class FieldDef {

    @Getter
    private final String name;
    @Getter
    private final String type;

    public FieldDef(JsonObject fdJson) {
        this.name = JsonHelper.getString(fdJson, "name");
        this.type = JsonHelper.getString(fdJson, "type");
    }

}
