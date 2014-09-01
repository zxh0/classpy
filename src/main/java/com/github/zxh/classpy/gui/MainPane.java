package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * Container of TreeView, HexPane and StatusBar.
 * 
 * |--------------------|
 * | TreeView | HexPane |
 * |--------------------|
 * | StatusBar          |
 * |--------------------|
 * 
 * @author zxh
 */
public class MainPane extends BorderPane {
    
    public MainPane(FileComponent file, FileHex hex) {
        Label statusBar = new Label(" ");
        super.setBottom(statusBar);
        
        SplitPane splitPane = buildSplitPane(file, hex, statusBar);
        super.setCenter(splitPane);
    }
    
    private static SplitPane buildSplitPane(FileComponent file, FileHex hex, Label statusBar) {
        TreeView<FileComponent> tree = buildClassTree(file);
        HexPane hexPane = buildHexPane(hex);
        listenTreeItemSelection(tree, hexPane, statusBar);
        
        SplitPane sp = new SplitPane();
        sp.getItems().add(tree);
        sp.getItems().add(hexPane);
        sp.setDividerPositions(0.1, 0.9);
        
        return sp;
    }
    
    private static TreeView<FileComponent> buildClassTree(FileComponent file) {
        FileComponentTreeItem root = new FileComponentTreeItem(file);
        root.setExpanded(true);
        
        TreeView<FileComponent> tree = new TreeView<>(root);
        tree.setMinWidth(200);
        
        return tree;
    }
    
    private static HexPane buildHexPane(FileHex hex) {
        HexPane pane = new HexPane(hex);
        // http://stackoverflow.com/questions/24983841/format-text-output-in-javafx
        pane.setFont(Font.font("Courier New", 14));
        return pane;
    }
    
    private static void listenTreeItemSelection(TreeView<FileComponent> tree, HexPane hexPane, Label statusBar) {
        tree.getSelectionModel().getSelectedItems().addListener(
            (ListChangeListener.Change<? extends TreeItem<FileComponent>> c) -> {
                if (c.next()) {
                    if (c.wasAdded()) {
                        TreeItem<FileComponent> node = c.getList().get(c.getFrom());
                        if (node != null && node.getParent() != null) {
                            FileComponent fc = node.getValue();
                            //System.out.println("select " + cc);
                            statusBar.setText(" " + fc.getClass().getSimpleName());
                            if (fc.getLength() > 0) {
                                hexPane.select(fc);
                            }
                        }
                    }
                }
            }
        );
    }
    
}
