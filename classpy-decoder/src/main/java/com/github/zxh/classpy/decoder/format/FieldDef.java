package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonObject;
import lombok.Getter;

public class FieldDef {

    @Getter
    private final String name;
    @Getter
    private final String type;

    @Getter
    private final ListWithoutLenType listWithoutLenType;

    public FieldDef(JsonObject fdJson) {
        this.name = JsonHelper.getString(fdJson, "name");
        this.type = JsonHelper.getString(fdJson, "type");
        if (this.type.startsWith("[")) {
            this.listWithoutLenType = new ListWithoutLenType(this.type);
        } else {
            this.listWithoutLenType = null;
        }
    }

}
