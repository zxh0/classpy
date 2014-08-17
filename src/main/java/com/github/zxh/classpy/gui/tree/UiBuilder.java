package com.github.zxh.classpy.gui.tree;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.gui.hex.ClassHex;
import com.github.zxh.classpy.gui.hex.HexPane;
import javafx.collections.ListChangeListener;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Build a TreeView from ClassFile.
 * 
 * @author zxh
 */
public class UiBuilder {
    
    public static SplitPane buildMainPane(ClassFile cf) {
        SplitPane sp = new SplitPane();
        
        TreeView<ClassComponent> tree = buildClassTree(cf);
        HexPane hexPane = buildHexPane(cf);
        
        sp.getItems().add(tree);
        sp.getItems().add(hexPane);
        
        tree.getSelectionModel().getSelectedItems().addListener(
            (ListChangeListener.Change<? extends TreeItem<ClassComponent>> c) -> {
                if (c.next()) {
                    if (c.wasAdded()) {
                        TreeItem<ClassComponent> node = c.getList().get(c.getFrom());
                        ClassComponent cc = node.getValue();
                        hexPane.select(cc);
                        //System.out.println("select " + cc);
                    }
                }
            }
        );
        
        return sp;
    }
    
    private static TreeView<ClassComponent> buildClassTree(ClassFile cf) {
        ClassComponentTreeItem root = new ClassComponentTreeItem(cf);
        root.setExpanded(true);
        return new TreeView<>(root);
    }
    
    private static HexPane buildHexPane(ClassFile cf) {
        ClassHex hex = new ClassHex(cf);
        return new HexPane(hex);
    }

}
