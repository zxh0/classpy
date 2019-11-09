package com.github.zxh.classpy.gui.fs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.stream.Collectors;

public class DirTreeItem extends TreeItem<DirTreeNode> {

    private boolean isFirstTimeChildren = true;

    public DirTreeItem(DirTreeNode root) {
        super(root);
    }

    @Override
    public boolean isLeaf() {
        return !getValue().hasSubNodes();
    }

    @Override
    public ObservableList<TreeItem<DirTreeNode>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<DirTreeNode>> buildChildren() {
        List<DirTreeItem> items = getValue().getSubNodes().stream()
                .map(DirTreeItem::new)
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(items);
    }

}
