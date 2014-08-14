package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ClassTreeItem extends TreeItem<ClassComponent> {

    public ClassTreeItem(ClassComponent cc) {
        super(cc);
    }
    
}
