package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 *
 * @author zxh
 */
public class HexPane extends TextArea {
    
    private final FileHex hex;
    
    public HexPane(FileHex hex) {
        super(hex.getHexText());
        this.hex = hex;
        setEditable(false);
        // http://stackoverflow.com/questions/24983841/format-text-output-in-javafx
        setFont(Font.font("Courier New", 14));
    }
    
    public void select(ClassComponent fc) {
        FileHex.Selection selection = hex.select(fc);
        positionCaret(selection.getStartPosition());
        selectPositionCaret(selection.getEndPosition());
    }
    
}
