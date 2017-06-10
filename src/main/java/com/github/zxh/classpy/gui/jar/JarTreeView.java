package com.github.zxh.classpy.gui.jar;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

public class JarTreeView {

    public static TreeView<Path> createTreeView(File jar) throws Exception {
        URI jarUri = new URI("jar", jar.toPath().toUri().toString(), null);
        FileSystem zipFs = FileSystems.newFileSystem(jarUri, new HashMap<>());
        TreeView<Path> jarTree = createTreeView(zipFs.getPath("/"));
        return jarTree;
        // todo: close zipFs
    }

    private static TreeView<Path> createTreeView(Path rootPath) {
        JarTreeItem rootItem = new JarTreeItem(rootPath);
        rootItem.setExpanded(true);

        TreeView<Path> tree = new TreeView<>(rootItem);
        tree.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    //System.out.println(getSelectedClass(new File("todo"), tree));
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
        tree.setMinWidth(200);

        return tree;
    }

    // jar:file:/absolute/location/of/yourJar.jar!/path/to/ClassName.class
    private static URL getSelectedClass(File jar, TreeView<Path> jarTree) throws MalformedURLException {
        TreeItem<Path> selectedItem = jarTree.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Path selectedPath = selectedItem.getValue();
            if (selectedPath.toString().endsWith(".class")) {
                String jarPath = jar.getAbsolutePath();
                if (!jarPath.startsWith("/")) {
                    // windows
                    jarPath = "/" + jarPath;
                }
                String classPath = selectedPath.toAbsolutePath().toString();
                String classUrl = String.format("jar:file:%s!%s", jarPath, classPath);
                classUrl = classUrl.replace('\\', '/');
                System.out.println(classUrl);
                return new URL(classUrl);
            }
        }
        return null;
    }
    
}
