package com.github.zxh.classpy.gui.support;

import javafx.scene.image.Image;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Supported file types.
 */
public enum FileType {

    JAVA_JAR("/jar.png", "Java JAR", "*.jar"),
    JAVA_CLASS("/java.png", "Java Class", "*.class"),
    LUA_BC("/lua.png", "Lua Binary Chunk", "*.luac"),
    WASM("/wasm.png", "WebAssembly Binary Code", "*.wasm"),
    BITCOIN_BLOCK("/bitcoin.png", "Bitcoin Block", "?"),
    UNKNOWN("/file.png", "Unknown", "*.*"),
    ;

    public final Image icon;
    public final ExtensionFilter filter;

    FileType(String icon, String description, String extension) {
        this.icon = ImageHelper.loadImage(icon);
        this.filter = new ExtensionFilter(description, extension);
    }

}
