package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.common.FilePart;
import java.math.BigInteger;
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
 * | StatusLabel BinLabel BytesBar|
 * |------------------------------|
 */
public class ParsedViewerPane extends BorderPane {
    
    private final TreeView<FilePart> tree;
    private final HexPane hexPane;
    private final Label statusLabel;
    private final Label binLabel;
    private final BytesBar bytesBar;

    public ParsedViewerPane(FilePart file, HexText hex) {
        tree = buildClassTree(file);
        hexPane = new HexPane(hex);
        statusLabel = new Label(" ");
        binLabel = new Label(" ");
        bytesBar = new BytesBar(file.getLength());
        bytesBar.setMaxHeight(statusLabel.getPrefHeight());
        bytesBar.setPrefWidth(100);
        
        super.setCenter(buildSplitPane());
        super.setBottom(buildStatusBar());
        listenTreeItemSelection();
        listenMouseClick();
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
        statusBar.setCenter(binLabel);
        statusBar.setRight(bytesBar);
        return statusBar;
    }
    
    private void listenTreeItemSelection() {
        tree.getSelectionModel().getSelectedItems().addListener(
            (ListChangeListener.Change<? extends TreeItem<FilePart>> c) -> {
                if (c.next() && c.wasAdded()) {
                    TreeItem<FilePart> node = c.getList().get(c.getFrom());
                    if (node != null && node.getParent() != null) {
                        FilePart fp = node.getValue();
                        //System.out.println("select " + cc);
                        statusLabel.setText(" " + fp.getClass().getSimpleName());
                        if (fp.getLength() > 0) {
                            hexPane.select(fp);
                            bytesBar.select(fp);
                        }
                    }
                }
            }
        );
    }

    private void listenMouseClick() {
        hexPane.setOnMouseClicked(e -> {
            String selectedText = hexPane.getSelectedText();
            if (selectedText.isEmpty()
                    || selectedText.contains("|")
                    || !selectedText.matches("[0-9a-fA-F ]+")) {
                binLabel.setText("");
                return;
            }

            String hex = selectedText.replace(" ", "");
            String bin = new BigInteger(hex, 16).toString(2);
            binLabel.setText(addUnderscore(bin));
        });
    }

    private static String addUnderscore(String binNum) {
        StringBuilder sb = new StringBuilder(binNum.length() * 2);
        for (int i = binNum.length() - 1, j = 1; i >= 0; i--, j++) {
            sb.append(binNum.charAt(i));
            if (j % 4 == 0 && i > 0) {
                sb.append('_');
            }
        }
        sb.append(" ");
        return sb.reverse().toString();
    }

}
