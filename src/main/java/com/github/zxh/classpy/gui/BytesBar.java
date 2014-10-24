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
        getChildren().add(new Line(0, getHeight()/2, getWidth(), getHeight()/2));
        getChildren().add(new Rectangle(getWidth() * fc.getOffset() / byteCount, 2,
                getWidth() * fc.getLength() / byteCount, getHeight() - 4));
    }
    
}
