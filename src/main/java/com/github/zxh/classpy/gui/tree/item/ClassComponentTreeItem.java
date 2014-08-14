package com.github.zxh.classpy.gui.tree.item;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.gui.tree.ClassTreeItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ClassComponentTreeItem extends LazyTreeItem {

    public ClassComponentTreeItem(ClassComponent c) {
        super(c);
    }

    @Override
    protected void buildChildren(ObservableList<TreeItem<ClassComponent>> children) {
        getValue().getSubComponents().forEach(sub -> {
            children.add(new ClassTreeItem(sub));
        });
    }
    
}
