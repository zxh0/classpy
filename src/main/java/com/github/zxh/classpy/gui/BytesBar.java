package com.github.zxh.classpy.gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import com.github.zxh.classpy.common.BytesComponent;

public class BytesBar extends Pane {
    
    private final int byteCount;
    
    public BytesBar(int byteCount) {
        this.byteCount = byteCount;
    }
    
    public void select(BytesComponent cc) {
        getChildren().clear();
        
        final double w = getWidth() - 4;
        final double h = getHeight();
        
        getChildren().add(new Line(0, h / 2, w, h / 2));
        getChildren().add(new Rectangle(w * cc.getOffset() / byteCount, 4,
                w * cc.getLength() / byteCount, h - 8));
    }
    
}
