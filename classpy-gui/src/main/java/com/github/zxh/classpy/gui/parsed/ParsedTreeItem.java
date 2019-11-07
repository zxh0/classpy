package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.common.FilePart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class ParsedTreeItem extends TreeItem<FilePart> {

    private boolean isFirstTimeChildren = true;

    public ParsedTreeItem(FilePart cc) {
        super(cc);
    }

    @Override
    public boolean isLeaf() {
        return getValue().getParts().isEmpty();
    }

    // build children lazily
    @Override
    public ObservableList<TreeItem<FilePart>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<FilePart>> buildChildren() {
        ObservableList<TreeItem<FilePart>> children = FXCollections.observableArrayList();
        getValue().getParts().forEach(sub -> {
            children.add(new ParsedTreeItem(sub));
        });
        return children;
    }
    
}
