package com.github.zxh.classpy.gui.tree.item;

import com.github.zxh.classpy.classfile.ClassComponent;
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
        return false;
    }
    
    @Override
    public ObservableList<TreeItem<ClassComponent>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        return super.getChildren();
    }

    protected abstract ObservableList<TreeItem<ClassComponent>> buildChildren();
    
}
