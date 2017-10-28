package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.helper.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import com.github.zxh.classpy.common.FileComponent;

/**
 * http://download.java.net/jdk8/jfxdocs/javafx/scene/control/TreeItem.html
 */
public class ParsedTreeItem extends TreeItem<FileComponent> {

    private boolean isFirstTimeChildren = true;

    public ParsedTreeItem(FileComponent cc) {
        super(cc);
    }

    @Override
    public boolean isLeaf() {
        return getValue().getComponents().isEmpty();
    }

    // build children lazily
    @Override
    public ObservableList<TreeItem<FileComponent>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            Log.log("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<FileComponent>> buildChildren() {
        ObservableList<TreeItem<FileComponent>> children = FXCollections.observableArrayList();
        getValue().getComponents().forEach(sub -> children.add(new ParsedTreeItem(sub)));
        return children;
    }
    
}
