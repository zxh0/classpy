package com.github.zxh.classpy.gui.jar;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class JarTreeItem extends TreeItem<JarTreeNode> {

    private boolean isFirstTimeChildren = true;
    
    public JarTreeItem(JarTreeNode root) {
        super(root);
    }

    @Override
    public boolean isLeaf() {
        return !getValue().hasSubNodes();
    }
    
    @Override
    public ObservableList<TreeItem<JarTreeNode>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<JarTreeNode>> buildChildren() {
        List<JarTreeItem> items = getValue().subNodes.stream()
                .map(JarTreeItem::new)
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(items);
    }

}
