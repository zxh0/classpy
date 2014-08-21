package com.github.zxh.classpy.gui.tree;

import com.github.zxh.classpy.common.FileComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 * Build children lazily.
 * http://download.java.net/jdk8/jfxdocs/javafx/scene/control/TreeItem.html
 * 
 * @author zxh
 */
public class FileComponentTreeItem extends TreeItem<FileComponent> {

    private boolean isFirstTimeChildren = true;
    
    public FileComponentTreeItem(FileComponent fc) {
        super(fc);
    }

    @Override
    public boolean isLeaf() {
        return getValue().getSubComponents().isEmpty();
    }
    
    @Override
    public ObservableList<TreeItem<FileComponent>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<FileComponent>> buildChildren() {
        ObservableList<TreeItem<FileComponent>> children = FXCollections.observableArrayList();
        getValue().getSubComponents().forEach(sub -> {
            children.add(new FileComponentTreeItem(sub));
        });
        return children;
    }
    
}
