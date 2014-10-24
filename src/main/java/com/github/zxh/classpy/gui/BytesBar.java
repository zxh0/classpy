package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author zxh
 */
public class BytesBar extends Pane {
    
    private final int byteCount;
    
    public BytesBar(int byteCount) {
        this.byteCount = byteCount;
    }
    
    public void select(FileComponent fc) {
        getChildren().clear();
        
        final double w = getWidth() - 4;
        final double h = getHeight();
        
        getChildren().add(new Line(0, h / 2, w, h / 2));
        getChildren().add(new Rectangle(w * fc.getOffset() / byteCount, 2,
                w * fc.getLength() / byteCount, h - 4));
    }
    
}
