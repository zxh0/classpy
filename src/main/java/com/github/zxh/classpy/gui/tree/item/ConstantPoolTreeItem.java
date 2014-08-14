package com.github.zxh.classpy.gui.tree.item;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.cp.ConstantPool;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ConstantPoolTreeItem extends LazyTreeItem {

    public ConstantPoolTreeItem(ConstantPool cc) {
        super(cc);
    }

    @Override
    protected void buildChildren(ObservableList<TreeItem<ClassComponent>> children) {
        // todo
    }
    
}
