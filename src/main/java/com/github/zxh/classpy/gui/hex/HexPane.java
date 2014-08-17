package com.github.zxh.classpy.gui.hex;

import javafx.scene.control.TextArea;

/**
 *
 * @author zxh
 */
public class HexPane extends TextArea {
    
    private final ClassHex hex;
    
    public HexPane(ClassHex hex) {
        super(hex.getHexString());
        this.hex = hex;
    }
    
}
