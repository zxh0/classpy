package com.github.zxh.classpy.decoder.format;

import com.google.gson.JsonElement;

public class FormatException extends RuntimeException {

    public FormatException(String msg) {
        super(msg);
    }

    public FormatException(String msg, JsonElement elem) {
        super(msg + ": " + jsonElemToStr(elem));
    }

    private static String jsonElemToStr(JsonElement elem) {
        String str = elem.toString();
        if (str.length() > 100) {
            return str.substring(0, 100) + " ...";
        } else {
            return str;
        }
    }

}
