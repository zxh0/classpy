package com.github.zxh.classpy.parser.format;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class FileFormatDef {

    @Getter
    private final String name;
    @Getter
    private final String version;
    @Getter
    private final String type;
    private final Map<String, TypeDef> types;

    public FileFormatDef(JsonObject ffJson) {
        this.name = JsonHelper.getString(ffJson, "name");
        this.version = JsonHelper.getString(ffJson, "version");
        this.type = JsonHelper.getString(ffJson, "type");
        this.types = getTypes(ffJson);
        checkTypes();
    }

    public TypeDef getType(String name) {
        if (!this.types.containsKey(name)) {
            throw new FormatException("type not found: " + name);
        }
        return this.types.get(name);
    }

    private static Map<String, TypeDef> getTypes(JsonObject ffJson) {
        Map<String, TypeDef> types = new HashMap<>();
        for (JsonElement typeDefJson : JsonHelper.getArray(ffJson, "types")) {
            TypeDef typeDef = new TypeDef(JsonHelper.toObject(typeDefJson));
            for (String name: typeDef.getNames()) {
                if (types.containsKey(name)) {
                    throw new FormatException("duplicated type names: " + name);
                }
                types.put(name, typeDef);
            }
        }
        return types;
    }

    private void checkTypes() {
        getType(this.type);
        // TODO
    }

}
