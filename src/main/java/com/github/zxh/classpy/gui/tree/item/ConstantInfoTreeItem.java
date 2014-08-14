package com.github.zxh.classpy.gui.tree.item;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.cp.ConstantInfo;
import com.github.zxh.classpy.classfile.cp.ConstantUtf8Info;
import com.github.zxh.classpy.gui.tree.ClassTreeItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ConstantInfoTreeItem extends LazyTreeItem {

    public ConstantInfoTreeItem(ConstantInfo c) {
        super(c);
    }

    @Override
    protected void buildChildren(ObservableList<TreeItem<ClassComponent>> children) {
        ConstantInfo c = (ConstantInfo) getValue();
        children.add(new ClassTreeItem(c.getTag()));
        if (c instanceof ConstantUtf8Info) {
            ConstantUtf8Info utf8 = (ConstantUtf8Info) c;
            children.add(new ClassTreeItem(utf8.getByteCount()));
            children.add(new ClassTreeItem(utf8.getBytes()));
        }
    }
    
}
