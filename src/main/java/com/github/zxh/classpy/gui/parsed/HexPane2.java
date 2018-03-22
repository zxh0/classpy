package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.common.FileComponent;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;

public class HexPane2 extends VirtualizedScrollPane<StyleClassedTextArea> {

    private final HexText hex;

    public HexPane2(HexText hex) {
        super(new StyleClassedTextArea());
        this.hex = hex;
        getContent().setEditable(false);
        getContent().setStyle("-fx-font-family: monospace; -fx-font-size: 14;");
        getContent().appendText(hex.getText());
        scrollYToPixel(0);
    }
    
    public void select(FileComponent cc) {
        HexText.Selection selection = hex.select(cc);
        getContent().selectRange(selection.getStartPosition(),
                selection.getEndPosition());
    }
    
}
