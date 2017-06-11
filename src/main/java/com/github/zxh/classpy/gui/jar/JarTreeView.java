package com.github.zxh.classpy.gui.jar;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

public class JarTreeView {

    private final File jarFile;
    private final FileSystem zipFs;
    private final TreeView<Path> treeView;

    public JarTreeView(File jarFile) throws Exception {
        this.jarFile = jarFile;
        this.zipFs = openZipFs(jarFile);
        this.treeView = createTreeView(zipFs);
    }

    public TreeView<Path> getTreeView() {
        return treeView;
    }

    public void closeZipFs() {
        try {
            System.out.println("close " + zipFs);
            zipFs.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private FileSystem openZipFs(File jarFile) throws Exception {
        URI jarUri = new URI("jar", jarFile.toPath().toUri().toString(), null);
        return FileSystems.newFileSystem(jarUri, new HashMap<>());
    }

    private TreeView<Path> createTreeView(FileSystem zipFs) {
        Path rootPath = zipFs.getPath("/");
        JarTreeItem rootItem = new JarTreeItem(rootPath);
        rootItem.setExpanded(true);

        TreeView<Path> tree = new TreeView<>(rootItem);
        tree.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    System.out.println(getSelectedClass());
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
        tree.setMinWidth(200);

        return tree;
    }

    // jar:file:/absolute/location/of/yourJar.jar!/path/to/ClassName.class
    private URL getSelectedClass() throws MalformedURLException {
        TreeItem<Path> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Path selectedPath = selectedItem.getValue();
            if (selectedPath.toString().endsWith(".class")) {
                String jarPath = jarFile.getAbsolutePath();
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
