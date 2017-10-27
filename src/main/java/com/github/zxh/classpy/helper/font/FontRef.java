package com.github.zxh.classpy.helper.font;

import javafx.scene.text.Font;

public class FontRef {
    public static FontRef of(Font font) {
        return new FontRef(font.getFamily(), font.getSize());
    }

    private String name;
    private double size;

    public FontRef(String name, double size) {
        this.name = name;
        this.size = size;
    }

    public FontRef(String name) {
        this(name, 0);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "-fx-font-family: " + name + ";" +
                (size != 0 ? "-fx-font-size: " + (int) size + ";" : "");
    }
}
