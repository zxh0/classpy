package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonObject;
import lombok.Getter;

public class NamedTypeDef extends TypeDef {

    @Getter
    private final String nameIndexType;
    @Getter
    private final String nameContainer;
    @Getter
    private final String unknownNameType;

    public NamedTypeDef(JsonObject tdJson) {
        super(tdJson);
        nameIndexType = JsonHelper.getString(tdJson, "nameIndexType");
        nameContainer = JsonHelper.getString(tdJson, "nameContainer");
        unknownNameType = JsonHelper.getString(tdJson, "unknownNameType");
    }

}
