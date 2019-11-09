package com.github.zxh.classpy.gui.fs;

import com.github.zxh.classpy.helper.UrlHelper;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.function.Consumer;

public class ZipTreeView {

    private final String zipURL;
    private final TreeView<ZipTreeNode> treeView;
    private Consumer<String> openFileHandler;

    public ZipTreeView(String zipURL, ZipTreeNode rootNode) {
        this.zipURL = zipURL;
        this.treeView = createTreeView(rootNode);
    }

    public TreeView<ZipTreeNode> getTreeView() {
        return treeView;
    }

    public void setOpenFileHandler(Consumer<String> openFileHandler) {
        this.openFileHandler = openFileHandler;
    }

    private TreeView<ZipTreeNode> createTreeView(ZipTreeNode rootNode) {
        ZipTreeItem rootItem = new ZipTreeItem(rootNode);
        rootItem.setExpanded(true);

        TreeView<ZipTreeNode> tree = new TreeView<>(rootItem);
        tree.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedFile = getSelectedFile();
                if (selectedFile != null && openFileHandler != null) {
                    System.out.println(selectedFile);
                    openFileHandler.accept(selectedFile);
                }
            }
        });

        return tree;
    }

    // jar:file:/absolute/location/of/yourJar.jar!/path/to/ClassName.class
    private String getSelectedFile() {
        TreeItem<ZipTreeNode> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ZipTreeNode selectedPath = selectedItem.getValue();
            if (selectedPath.toString().endsWith(".class")) {
                String fileURL = String.format("%s:%s!%s",
                        UrlHelper.getFileSuffix(zipURL), zipURL, selectedPath.path);
                fileURL = fileURL.replace('\\', '/');
                //System.out.println(classUrl);
                return fileURL;
            }
        }
        return null;
    }

}
