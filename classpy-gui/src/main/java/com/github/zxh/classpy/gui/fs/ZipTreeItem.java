package com.github.zxh.classpy.gui.fs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.stream.Collectors;

public class ZipTreeItem extends TreeItem<ZipTreeNode> {

    private boolean isFirstTimeChildren = true;

    public ZipTreeItem(ZipTreeNode root) {
        super(root);
    }

    @Override
    public boolean isLeaf() {
        return !getValue().hasSubNodes();
    }

    @Override
    public ObservableList<TreeItem<ZipTreeNode>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());
            super.getChildren().setAll(buildChildren());
        }

        return super.getChildren();
    }

    private ObservableList<TreeItem<ZipTreeNode>> buildChildren() {
        List<ZipTreeItem> items = getValue().subNodes.stream()
                .map(ZipTreeItem::new)
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(items);
    }

}
