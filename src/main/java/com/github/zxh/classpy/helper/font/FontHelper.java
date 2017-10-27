package com.github.zxh.classpy.helper.font;

import java.util.HashSet;

import javafx.scene.text.Font;

public class FontHelper {
    private FontHelper() {
    }

    public static final Font uiFont;

    public static final Font textFont;

    static {
        uiFont = Font.loadFont(FontHelper.class.getResourceAsStream("/UIFont.ttf"), 12);
        textFont = Font.loadFont(FontHelper.class.getResourceAsStream("/TextFont.ttf"), 14);

    }


}
