package com.github.zxh.classpy.gui.fs;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.function.Consumer;

public class DirTreeView {

    private final TreeView<DirTreeNode> treeView;
    private Consumer<File> openFileHandler;

    public DirTreeView(DirTreeNode rootNode) {
        this.treeView = createTreeView(rootNode);
    }

    public TreeView<DirTreeNode> getTreeView() {
        return treeView;
    }

    public void setOpenFileHandler(Consumer<File> openFileHandler) {
        this.openFileHandler = openFileHandler;
    }

    private TreeView<DirTreeNode> createTreeView(DirTreeNode rootNode) {
        DirTreeItem rootItem = new DirTreeItem(rootNode);
        rootItem.setExpanded(false);

        TreeView<DirTreeNode> tree = new TreeView<>(rootItem);
        tree.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                File selectedFile = getSelectedFile();
                if (selectedFile != null && openFileHandler != null) {
                    System.out.println(selectedFile);
                    openFileHandler.accept(selectedFile);
                }
            }
        });

        return tree;
    }

    private File getSelectedFile() {
        TreeItem<DirTreeNode> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            DirTreeNode selectedPath = selectedItem.getValue();
            return selectedPath.path.toFile();
        }
        return null;
    }

    public static DirTreeView create(File dir) {
        return new DirTreeView(new DirTreeNode(dir.toPath()));
    }

}
