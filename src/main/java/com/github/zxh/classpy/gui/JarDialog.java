package com.github.zxh.classpy.gui;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zxh
 */
public class JarDialog {
    
    public static void showDialog(File jar) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        URI uri = new URI("jar", jar.toPath().toUri().toString(), null);  
        try (FileSystem zipFs = FileSystems.newFileSystem(uri, new HashMap<>())) {
            Path rootPath = zipFs.getPath("/");
            TreeView<Path> tree = createTreeView(rootPath);
            Scene scene = new Scene(tree, 300, 180);
            
            stage.setScene(scene);
            stage.setTitle("Jar");
            stage.showAndWait();
        }
    }
    
    private static TreeView<Path> createTreeView(Path rootPath) {
        JarTreeItem rootItem = new JarTreeItem(rootPath);
        rootItem.setExpanded(true);
        
        TreeView<Path> tree = new TreeView<>(rootItem);
        tree.setMinWidth(200);
        
        return tree;
    }
    
}
