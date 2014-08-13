package com.github.zxh.classpy.gui;

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
    
    public TreeView<String> build() {
//        TreeView<String> root = new TreeView<>();
//        root.getChildren().add(null);
        
        TreeItem<String> root = new TreeItem<>();
        
        TreeItem<String> magic = new TreeItem<>();
        
        TreeView<String> tree = new TreeView<>(root);
        
        return tree;
    }
    
}
