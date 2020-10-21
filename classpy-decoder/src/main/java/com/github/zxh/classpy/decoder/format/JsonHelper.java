package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonHelper {

    public static Integer getInteger(JsonObject obj, String fieldName) {
        JsonElement field = getField(obj, fieldName);
        if (!field.isJsonPrimitive()) {
            throw new FormatException(String.format("'%s' is not Integer", fieldName), field);
        }
        return field.getAsInt();
    }
    public static String getString(JsonObject obj, String fieldName) {
        JsonElement field = getField(obj, fieldName);
        if (!field.isJsonPrimitive()) {
            throw new FormatException(String.format("'%s' is not String", fieldName), field);
        }
        return field.getAsString();
    }
    public static JsonObject getObject(JsonObject obj, String fieldName) {
        JsonElement field = getField(obj, fieldName);
        if (!field.isJsonObject()) {
            throw new FormatException(String.format("'%s' is not Object", fieldName), field);
        }
        return field.getAsJsonObject();
    }
    public static JsonArray getArray(JsonObject obj, String fieldName) {
        JsonElement field = getField(obj, fieldName);
        if (!field.isJsonArray()) {
            throw new FormatException(String.format("'%s' is not Array", fieldName), field);
        }
        return field.getAsJsonArray();
    }
    public static JsonElement getField(JsonObject obj, String fieldName) {
        JsonElement field = obj.get(fieldName);
        if (field == null) {
            throw new FormatException(String.format("'%s' not found", fieldName), obj);
        }
        return field;
    }


    public static String toString(JsonElement elem) {
        if (!elem.isJsonPrimitive()) {
            throw new FormatException("not a String", elem);
        }
        return elem.getAsString();
    }
    public static JsonObject toObject(JsonElement elem) {
        if (!elem.isJsonObject()) {
            throw new FormatException("not an Object", elem);
        }
        return elem.getAsJsonObject();
    }
    public static JsonArray toArray(JsonElement elem) {
        if (!elem.isJsonArray()) {
            throw new FormatException("not an Array", elem);
        }
        return elem.getAsJsonArray();
    }

}
