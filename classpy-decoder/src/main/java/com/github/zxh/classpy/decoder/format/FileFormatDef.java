package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileFormatDef {

    @Getter
    private final String name;
    @Getter
    private final String version;
    @Getter
    private final String mainType;
    @Getter
    private final ByteOrder byteOrder;

    private final Set<String> builtinTypes;
    private final Map<String, TypeDef> definedTypes;

    public FileFormatDef(JsonObject ffJson) {
        this.name = JsonHelper.getString(ffJson, "name");
        this.version = JsonHelper.getString(ffJson, "version");
        this.mainType = JsonHelper.getString(ffJson, "type");
        this.byteOrder = getByteOrder(ffJson);
        this.builtinTypes = getBuiltinTypes(ffJson);
        this.definedTypes = getDefinedTypes(ffJson);
        checkTypes();
    }

    public boolean hasTypeDef(String name) {
        return this.definedTypes.containsKey(name);
    }
    public TypeDef getTypeDef(String name) {
        if (!this.definedTypes.containsKey(name)) {
            throw new FormatException("type not found: " + name);
        }
        return this.definedTypes.get(name);
    }

    private static ByteOrder getByteOrder(JsonObject ffJson) {
        String endianness = JsonHelper.getString(ffJson, "endianness");
        switch (endianness.toLowerCase()) {
            case "big-endian": return ByteOrder.BIG_ENDIAN;
            case "little-endian": return ByteOrder.LITTLE_ENDIAN;
            default: throw new FormatException("invalid endianness: " + endianness);
        }
    }

    private static Map<String, TypeDef> getDefinedTypes(JsonObject ffJson) {
        Map<String, TypeDef> types = new HashMap<>();
        for (JsonElement typeDefJson : JsonHelper.getArray(ffJson, "types")) {
            TypeDef typeDef = TypeDef.parse(JsonHelper.toObject(typeDefJson));
            for (String name: typeDef.getNames()) {
                if (types.containsKey(name)) {
                    throw new FormatException("duplicated type names: " + name);
                }
                types.put(name, typeDef);
            }
        }
        return types;
    }

    private static Set<String> getBuiltinTypes(JsonObject ffJson) {
        Set<String> builtinTypes = new HashSet<>();
        for (JsonElement type : JsonHelper.getArray(ffJson, "builtin")) {
            builtinTypes.add(JsonHelper.toString(type));
        }
        return builtinTypes;
    }

    private void checkTypes() {
        getTypeDef(this.mainType);
        // TODO
    }

}
