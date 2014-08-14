package com.github.zxh.classpy.gui.tree.item;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.cp.ConstantPool;
import com.github.zxh.classpy.gui.tree.ClassTreeItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ConstantPoolTreeItem extends LazyTreeItem {

    public ConstantPoolTreeItem(ConstantPool cp) {
        super(cp);
    }

    @Override
    protected void buildChildren(ObservableList<TreeItem<ClassComponent>> children) {
        ConstantPool cp = (ConstantPool) getValue();
        cp.forEach(c -> {
            children.add(new ClassTreeItem(c));
        });
    }
    
}
