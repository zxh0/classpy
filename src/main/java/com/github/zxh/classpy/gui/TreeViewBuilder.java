package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassFile;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author zxh
 */
public class TreeViewBuilder {
    
    private final ClassFile cf;

    public TreeViewBuilder(ClassFile cf) {
        this.cf = cf;
    }
    
    public TreeView<ClassComponent> build() {
//        TreeView<String> root = new TreeView<>();
//        root.getChildren().add(null);
        
        TreeItem<ClassComponent> root = new TreeItem<>();
        
        ClassComponentTreeItem magic = new ClassComponentTreeItem(cf.getMagic());
        root.getChildren().add(magic);
        
        TreeView<ClassComponent> tree = new TreeView<>(root);
        
        return tree;
    }
    
}
