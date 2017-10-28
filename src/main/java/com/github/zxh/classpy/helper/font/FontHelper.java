package com.github.zxh.classpy.helper.font;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

import com.github.zxh.classpy.helper.Log;
import javafx.scene.text.Font;

public class FontHelper {
    private FontHelper() {
    }

    static {
        Log.log("loading fonts...");
        try {
            awtTextFont = java.awt.Font.createFont(
                    java.awt.Font.PLAIN,
                    FontHelper.class.getResourceAsStream("/TextFont.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException e) {
            Log.log(e);
        }
    }


    public static Font uiFont = Font.loadFont(FontHelper.class.getResourceAsStream("/UIFont.ttf"), 12);

    public static Font textFont = Font.loadFont(FontHelper.class.getResourceAsStream("/TextFont.ttf"), 14);

    public static java.awt.Font awtTextFont;
}
