package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.helper.font.FontHelper;
import javafx.scene.control.TextArea;
import com.github.zxh.classpy.common.FileComponent;

public class HexPane extends TextArea {
    
    private final HexText hex;
    
    public HexPane(HexText hex) {
        super(hex.getText());
        this.hex = hex;
        setEditable(false);
        // http://stackoverflow.com/questions/24983841/format-text-output-in-javafx
        setFont(FontHelper.textFont);
    }
    
    public void select(FileComponent cc) {
        HexText.Selection selection = hex.select(cc);
        positionCaret(selection.getStartPosition());
        selectPositionCaret(selection.getEndPosition());
    }
    
}
