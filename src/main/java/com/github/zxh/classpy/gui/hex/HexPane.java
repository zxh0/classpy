package com.github.zxh.classpy.gui.hex;

import com.github.zxh.classpy.common.FileComponent;
import javafx.scene.control.TextArea;

/**
 *
 * @author zxh
 */
public class HexPane extends TextArea {
    
    private final ClassHex hex;
    
    public HexPane(ClassHex hex) {
        super(hex.getHexText());
        this.hex = hex;
        setEditable(false);
    }
    
    public void select(FileComponent fc) {
        ClassHex.Selection selection = hex.select(fc);
        positionCaret(selection.getStartPosition());
        selectPositionCaret(selection.getEndPosition());
    }
    
}
