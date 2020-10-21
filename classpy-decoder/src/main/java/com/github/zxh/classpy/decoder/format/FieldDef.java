package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonObject;
import lombok.Getter;

public class FieldDef {

    @Getter
    private final String name;
    @Getter
    private final String type;

    @Getter
    private final ListWithoutLenTypeDef listWithoutLenTypeDef;

    public FieldDef(JsonObject fdJson) {
        this.name = JsonHelper.getString(fdJson, "name");
        this.type = JsonHelper.getString(fdJson, "type");
        if (this.type.startsWith("[")) {
            this.listWithoutLenTypeDef = new ListWithoutLenTypeDef(this.type);
        } else {
            this.listWithoutLenTypeDef = null;
        }
    }

}
