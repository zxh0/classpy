package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ClassComponentTreeItem extends TreeItem<ClassComponent> {

    public ClassComponentTreeItem(ClassComponent cc) {
        super(cc);
    }
    
}
