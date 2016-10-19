package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.common.BytesComponent;
import javafx.scene.image.Image;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Supported file types.
 */
public enum FileType {

    JAVA_JAR(ImageHelper.loadImage("/jar.png"), new ExtensionFilter("Java JAR", "*.jar")),
    JAVA_CLASS(ImageHelper.loadImage("/java.png"), new ExtensionFilter("Java Class", "*.class")),
    LUAC_OUT(ImageHelper.loadImage("/lua.png"), new ExtensionFilter("luac.out", "*.luac")),
    ;

    public final Image icon;
    public final ExtensionFilter filter;

    private FileType(Image icon, ExtensionFilter filter) {
        this.icon = icon;
        this.filter = filter;
    }

    public static FileType typeOf(BytesComponent root) {
        if (root instanceof ClassFile) {
            return JAVA_CLASS;
        } else {
            return LUAC_OUT;
        }
    }

}
