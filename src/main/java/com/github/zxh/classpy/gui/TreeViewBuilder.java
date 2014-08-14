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
        
        root.getChildren().add(new ClassTreeItem(cf.getMagic()));
        root.getChildren().add(new ClassTreeItem(cf.getMinorVersion()));
        root.getChildren().add(new ClassTreeItem(cf.getMajorVersion()));
        root.getChildren().add(new ClassTreeItem(cf.getConstantPoolCount()));
        // cp
        root.getChildren().add(new ClassTreeItem(cf.getAccessFlags()));
        root.getChildren().add(new ClassTreeItem(cf.getThisClass()));
        root.getChildren().add(new ClassTreeItem(cf.getSuperClass()));
        root.getChildren().add(new ClassTreeItem(cf.getInterfacesCount()));
        root.getChildren().add(new ClassTreeItem(cf.getFieldsCount()));
        root.getChildren().add(new ClassTreeItem(cf.getMethodsCount()));
        root.getChildren().add(new ClassTreeItem(cf.getAttributesCount()));
        
        
        TreeView<ClassComponent> tree = new TreeView<>(root);
        
        return tree;
    }
    
}
