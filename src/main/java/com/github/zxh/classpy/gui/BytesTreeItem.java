package com.github.zxh.classpy.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import com.github.zxh.classpy.common.BytesComponent;

/**
 * FileComponentTreeItem.
 * Build children lazily.
 * http://download.java.net/jdk8/jfxdocs/javafx/scene/control/TreeItem.html
 */
public class BytesTreeItem extends TreeItem<BytesComponent> {

    private boolean isFirstTimeChildren = true;
    
    public BytesTreeItem(BytesComponent cc) {
        super(cc);
    }

    @Override
    public boolean isLeaf() {
        return getValue().getComponents().isEmpty();
    }
    
    @Override
    public ObservableList<TreeItem<BytesComponent>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<BytesComponent>> buildChildren() {
        ObservableList<TreeItem<BytesComponent>> children = FXCollections.observableArrayList();
        getValue().getComponents().forEach(sub -> {
            children.add(new BytesTreeItem(sub));
        });
        return children;
    }
    
}
