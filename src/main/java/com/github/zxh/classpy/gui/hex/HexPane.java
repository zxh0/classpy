package com.github.zxh.classpy.gui.hex;

import com.github.zxh.classpy.classfile.ClassComponent;
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
    
    public void select(ClassComponent cc) {
        ClassHex.Selection selection = hex.select(cc);
        positionCaret(selection.getStartPosition());
        selectPositionCaret(selection.getEndPosition());
    }
    
}
