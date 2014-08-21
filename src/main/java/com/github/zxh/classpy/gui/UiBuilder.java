package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.gui.hex.ClassHex;
import com.github.zxh.classpy.gui.hex.HexPane;
import com.github.zxh.classpy.gui.tree.FileComponentTreeItem;
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
        
        TreeView<FileComponent> tree = buildClassTree(cf);
        HexPane hexPane = buildHexPane(cf);
        
        sp.getItems().add(tree);
        sp.getItems().add(hexPane);
        sp.setDividerPositions(0.1, 0.9);
        
        tree.getSelectionModel().getSelectedItems().addListener(
            (ListChangeListener.Change<? extends TreeItem<FileComponent>> c) -> {
                if (c.next()) {
                    if (c.wasAdded()) {
                        TreeItem<FileComponent> node = c.getList().get(c.getFrom());
                        if (node != null) {
                            FileComponent fc = node.getValue(); // NPE
                            //System.out.println("select " + cc);
                            if (!(fc instanceof ClassFile)) {
                                hexPane.select(fc);
                            }
                        }
                    }
                }
            }
        );
        
        return sp;
    }
    
    private static TreeView<FileComponent> buildClassTree(ClassFile cf) {
        FileComponentTreeItem root = new FileComponentTreeItem(cf);
        root.setExpanded(true);
        
        TreeView<FileComponent> tree = new TreeView<>(root);
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
