package com.github.zxh.classpy.gui.tree;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.gui.tree.item.ConstantPoolTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Build a TreeView from ClassFile.
 * 
 * @author zxh
 */
public class ClassTreeBuilder {
    
    private final ClassFile cf;

    public ClassTreeBuilder(ClassFile cf) {
        this.cf = cf;
    }
    
    public TreeView<ClassComponent> build() {
        TreeItem<ClassComponent> root = new TreeItem<>(cf);
        root.setExpanded(true);
        
        root.getChildren().add(new ClassTreeItem(cf.getMagic()));
        root.getChildren().add(new ClassTreeItem(cf.getMinorVersion()));
        root.getChildren().add(new ClassTreeItem(cf.getMajorVersion()));
        root.getChildren().add(new ClassTreeItem(cf.getConstantPoolCount()));
        root.getChildren().add(new ConstantPoolTreeItem(cf.getConstantPool()));
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
