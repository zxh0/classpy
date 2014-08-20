package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.gui.hex.ClassHex;
import com.github.zxh.classpy.gui.hex.HexPane;
import com.github.zxh.classpy.gui.tree.ClassComponentTreeItem;
import javafx.collections.ListChangeListener;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Font;

/**
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
        sp.setDividerPositions(0.1, 0.9);
        
        tree.getSelectionModel().getSelectedItems().addListener(
            (ListChangeListener.Change<? extends TreeItem<ClassComponent>> c) -> {
                if (c.next()) {
                    if (c.wasAdded()) {
                        TreeItem<ClassComponent> node = c.getList().get(c.getFrom());
                        if (node != null) {
                            ClassComponent cc = node.getValue(); // NPE
                            //System.out.println("select " + cc);
                            if (!(cc instanceof ClassFile)) {
                                hexPane.select(cc);
                            }
                        }
                    }
                }
            }
        );
        
        return sp;
    }
    
    private static TreeView<ClassComponent> buildClassTree(ClassFile cf) {
        ClassComponentTreeItem root = new ClassComponentTreeItem(cf);
        root.setExpanded(true);
        
        TreeView<ClassComponent> tree = new TreeView<>(root);
        tree.setMinWidth(200);
        
        return tree;
    }
    
    private static HexPane buildHexPane(ClassFile cf) {
        ClassHex hex = new ClassHex(cf);
        HexPane pane = new HexPane(hex);
        // http://stackoverflow.com/questions/24983841/format-text-output-in-javafx
        pane.setFont(Font.font("Courier New", 14));
        return pane;
    }
    
}
