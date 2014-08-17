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
        super(hex.getHexString());
        this.hex = hex;
    }
    
    public void select(ClassComponent cc) {
        positionCaret(cc.getOffset());
        selectPositionCaret(cc.getOffset() + cc.getLength());
    }
    
}
