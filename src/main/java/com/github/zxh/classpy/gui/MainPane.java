package com.github.zxh.classpy.gui;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.gui.support.HexText;

/**
 * Container of TreeView, HexPane and StatusBar.
 * 
 * |------------------------------|
 * | TreeView      |      HexPane |
 * |               |              |
 * |------------------------------|
 * | StatusLabel          BytesBar|
 * |------------------------------|
 */
public class MainPane extends BorderPane {
    
    private final TreeView<BytesComponent> tree;
    private final HexPane hexPane;
    private final Label statusLabel;
    private final BytesBar bytesBar;
    
    public MainPane(BytesComponent file, HexText hex) {
        tree = buildClassTree(file);
        hexPane = new HexPane(hex);
        statusLabel = new Label(" ");
        bytesBar = new BytesBar(file.getLength());
        bytesBar.setMaxHeight(statusLabel.getPrefHeight());
        bytesBar.setPrefWidth(100);
        
        super.setCenter(buildSplitPane());
        super.setBottom(buildStatusBar());
        listenTreeItemSelection();
    }

    private static TreeView<BytesComponent> buildClassTree(BytesComponent file) {
        BytesTreeItem root = new BytesTreeItem(file);
        root.setExpanded(true);
        
        TreeView<BytesComponent> tree = new TreeView<>(root);
        tree.setMinWidth(200);
        
        return tree;
    }
    
    private SplitPane buildSplitPane() {
        SplitPane sp = new SplitPane();
        sp.getItems().add(tree);
        sp.getItems().add(hexPane);
        sp.setDividerPositions(0.3, 0.7);
        return sp;
    }
    
    private BorderPane buildStatusBar() {
        BorderPane statusBar = new BorderPane();
        statusBar.setLeft(statusLabel);
        statusBar.setRight(bytesBar);
        return statusBar;
    }
    
    private void listenTreeItemSelection() {
        tree.getSelectionModel().getSelectedItems().addListener(
            (ListChangeListener.Change<? extends TreeItem<BytesComponent>> c) -> {
                if (c.next()) {
                    if (c.wasAdded()) {
                        TreeItem<BytesComponent> node = c.getList().get(c.getFrom());
                        if (node != null && node.getParent() != null) {
                            BytesComponent cc = node.getValue();
                            //System.out.println("select " + cc);
                            statusLabel.setText(" " + cc.getClass().getSimpleName());
                            if (cc.getLength() > 0) {
                                hexPane.select(cc);
                                bytesBar.select(cc);
                            }
                        }
                    }
                }
            }
        );
    }
    
}
