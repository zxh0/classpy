package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.common.FileComponent;
import javafx.scene.image.Image;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Supported file types.
 */
public enum FileType {

    JAVA_JAR("/jar.png", "Java JAR", "*.jar"),
    JAVA_CLASS("/java.png", "Java Class", "*.class"),
    LUA_BC("/lua.png", "Lua Binary Chunk", "*.luac"),
    UNKNOWN("/file.png", "Unknown", "*.*"),
    ;

    public final Image icon;
    public final ExtensionFilter filter;

    private FileType(String icon, String description, String extension) {
        this.icon = ImageHelper.loadImage(icon);
        this.filter = new ExtensionFilter(description, extension);
    }

    public static FileType typeOf(FileComponent root) {
        if (root instanceof ClassFile) {
            return JAVA_CLASS;
        } else {
            return LUA_BC;
        }
    }

}
