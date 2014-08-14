package com.github.zxh.classpy.gui.tree;

import com.github.zxh.classpy.classfile.ClassComponent;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 * 
 * http://download.java.net/jdk8/jfxdocs/javafx/scene/control/TreeItem.html
 */
public class ClassTreeItem extends TreeItem<ClassComponent> {

    public ClassTreeItem(ClassComponent cc) {
        super(cc);
    }
    
}
