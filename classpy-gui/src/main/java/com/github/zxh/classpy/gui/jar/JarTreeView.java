package com.github.zxh.classpy.gui.jar;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.util.function.Consumer;

public class JarTreeView {

    private final URL jarURL;
    private final TreeView<JarTreeNode> treeView;
    private Consumer<String> openClassHandler;

    public JarTreeView(URL jarURL, JarTreeNode rootNode) {
        this.jarURL = jarURL;
        this.treeView = createTreeView(rootNode);
    }

    public TreeView<JarTreeNode> getTreeView() {
        return treeView;
    }

    public void setOpenClassHandler(Consumer<String> openClassHandler) {
        this.openClassHandler = openClassHandler;
    }

    private TreeView<JarTreeNode> createTreeView(JarTreeNode rootNode) {
        JarTreeItem rootItem = new JarTreeItem(rootNode);
        rootItem.setExpanded(true);

        TreeView<JarTreeNode> tree = new TreeView<>(rootItem);
        tree.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedClass = getSelectedClass();
                if (selectedClass != null && openClassHandler != null) {
                    System.out.println(selectedClass);
                    openClassHandler.accept(selectedClass);
                }
            }
        });

        return tree;
    }

    // jar:file:/absolute/location/of/yourJar.jar!/path/to/ClassName.class
    private String getSelectedClass() {
        TreeItem<JarTreeNode> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            JarTreeNode selectedPath = selectedItem.getValue();
            if (selectedPath.toString().endsWith(".class")) {
                String classUrl = String.format("jar:%s!%s", jarURL, selectedPath.path);
                classUrl = classUrl.replace('\\', '/');
                //System.out.println(classUrl);
                return classUrl;
            }
        }
        return null;
    }

    public static boolean isOpen(File jarFile) throws Exception {
        URI jarUri = new URI("jar", jarFile.toPath().toUri().toString(), null);
        try {
            return FileSystems.getFileSystem(jarUri) != null;
        } catch (FileSystemNotFoundException e) {
            return false;
        }
    }

}
