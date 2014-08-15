package com.github.zxh.classpy.gui.tree;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassFile;
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
        ClassComponentTreeItem root = new ClassComponentTreeItem(cf);
        root.setExpanded(true);
        
        TreeView<ClassComponent> tree = new TreeView<>(root);
        
        return tree;
    }
    
}
