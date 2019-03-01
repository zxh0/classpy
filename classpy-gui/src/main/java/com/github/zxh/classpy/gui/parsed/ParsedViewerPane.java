package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.common.FilePart;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

/**
 * Container of TreeView, HexPane, StatusBar and BytesBar.
 * 
 * |------------------------------|
 * | TreeView      |      HexPane |
 * |               |              |
 * |------------------------------|
 * | StatusLabel          BytesBar|
 * |------------------------------|
 */
public class ParsedViewerPane extends BorderPane {
    
    private final TreeView<FilePart> tree;
    private final HexPane hexPane;
    private final Label statusLabel;
    private final BytesBar bytesBar;
    
    public ParsedViewerPane(FilePart file, HexText hex) {
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

    private static TreeView<FilePart> buildClassTree(FilePart file) {
        ParsedTreeItem root = new ParsedTreeItem(file);
        root.setExpanded(true);
        
        TreeView<FilePart> tree = new TreeView<>(root);
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
            (ListChangeListener.Change<? extends TreeItem<FilePart>> c) -> {
                if (c.next() && c.wasAdded()) {
                    TreeItem<FilePart> node = c.getList().get(c.getFrom());
                    if (node != null && node.getParent() != null) {
                        FilePart cc = node.getValue();
                        //System.out.println("select " + cc);
                        statusLabel.setText(" " + cc.getClass().getSimpleName());
                        if (cc.getLength() > 0) {
                            hexPane.select(cc);
                            bytesBar.select(cc);
                        }
                    }
                }
            }
        );
    }
    
}
