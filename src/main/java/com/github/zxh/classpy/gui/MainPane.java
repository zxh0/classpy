package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

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
        TreeView<FileComponent> tree = buildClassTree(file);
        HexPane hexPane = new HexPane(hex);
        Label statusLabel = new Label(" ");
        BytesBar bytesBar = new BytesBar(file.getLength());
        bytesBar.setMaxHeight(statusLabel.getPrefHeight());
        bytesBar.setPrefWidth(100);
        
        listenTreeItemSelection(tree, hexPane, statusLabel, bytesBar);
        super.setCenter(buildSplitPane(tree, hexPane));
        super.setBottom(buildStatusBar(statusLabel, bytesBar));
    }
    
    private static SplitPane buildSplitPane(TreeView<FileComponent> tree, HexPane hexPane) {
        SplitPane sp = new SplitPane();
        sp.getItems().add(tree);
        sp.getItems().add(hexPane);
        sp.setDividerPositions(0.1, 0.9);
        return sp;
    }
    
    private static BorderPane buildStatusBar(Label statusLabel, BytesBar bytesBar) {
        BorderPane statusBar = new BorderPane();
        statusBar.setLeft(statusLabel);
        statusBar.setRight(bytesBar);
        return statusBar;
    }
    
    private static TreeView<FileComponent> buildClassTree(FileComponent file) {
        FileComponentTreeItem root = new FileComponentTreeItem(file);
        root.setExpanded(true);
        
        TreeView<FileComponent> tree = new TreeView<>(root);
        tree.setMinWidth(200);
        
        return tree;
    }
    
    private static void listenTreeItemSelection(TreeView<FileComponent> tree,
            HexPane hexPane, Label statusBar, BytesBar bytesBar) {
        
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
                                bytesBar.select(fc);
                            }
                        }
                    }
                }
            }
        );
    }
    
}
