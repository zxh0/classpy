package com.github.zxh.classpy.gui.parsed;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import com.github.zxh.classpy.common.FileComponent;

public class HexPane extends TextArea {
    
    private final HexText hex;
    
    public HexPane(HexText hex) {
        super(hex.getText());
        this.hex = hex;
        setEditable(false);
        // http://stackoverflow.com/questions/24983841/format-text-output-in-javafx
        setFont(Font.font("Courier New", 14));
    }
    
    public void select(FileComponent cc) {
        HexText.Selection selection = hex.select(cc);
        positionCaret(selection.getStartPosition());
        selectPositionCaret(selection.getEndPosition());
    }
    
}
