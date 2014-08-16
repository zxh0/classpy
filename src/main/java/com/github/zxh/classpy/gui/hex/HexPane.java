package com.github.zxh.classpy.gui.hex;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author zxh
 */
public class HexPane extends BorderPane {
    
    public HexPane(ClassHex hex) {
        TextArea hexArea = new TextArea(hex.getHexString());
        TextArea asciiArea = new TextArea(hex.getAsciiString());
        
        setLeft(hexArea);
        setRight(asciiArea);
        
    }
    
}
