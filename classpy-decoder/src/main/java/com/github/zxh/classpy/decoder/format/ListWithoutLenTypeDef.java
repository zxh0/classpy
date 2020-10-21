package com.github.zxh.classpy.decoder.format;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListWithoutLenTypeDef {

    private static final Pattern PATTERN = Pattern.compile("\\[(\\w+)(-1)?\\](\\w+)");

    @Getter
    private final String lenFieldName;
    @Getter
    private final String elemTypeName;

    private final boolean minusOne;

    public ListWithoutLenTypeDef(String type) {
        Matcher matcher = PATTERN.matcher(type);
        if (!matcher.matches()) {
            throw new FormatException("invalid type: " + type);
        }
        this.lenFieldName = matcher.group(1);
        this.elemTypeName = matcher.group(3);
        this.minusOne = "-1".equals(matcher.group(2));
    }

    public boolean hasMinusOne() {
        return minusOne;
    }

}
