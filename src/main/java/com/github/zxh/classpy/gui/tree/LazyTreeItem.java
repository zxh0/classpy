package com.github.zxh.classpy.gui.tree;

import com.github.zxh.classpy.classfile.ClassComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 * Build children lazily.
 * http://download.java.net/jdk8/jfxdocs/javafx/scene/control/TreeItem.html
 * 
 * @author zxh
 */
public abstract class LazyTreeItem extends TreeItem<ClassComponent> {

    private boolean isFirstTimeChildren = true;
    
    public LazyTreeItem(ClassComponent cc) {
        super(cc);
    }

    @Override
    public boolean isLeaf() {
        return getValue().getSubComponents().isEmpty();
    }
    
    @Override
    public ObservableList<TreeItem<ClassComponent>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            ObservableList<TreeItem<ClassComponent>> children = FXCollections.observableArrayList();
            buildChildren(children);
            super.getChildren().setAll(children);
        }
        return super.getChildren();
    }

    protected abstract void buildChildren(ObservableList<TreeItem<ClassComponent>> children);
    
}
