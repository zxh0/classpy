package com.github.zxh.classpy.gui;

import java.nio.file.Path;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zxh
 */
public class JarDialog {
    
    public static void showDialog(Path rootPath) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        TreeView<Path> tree = createTreeView(rootPath);
        Scene scene = new Scene(tree, 300, 180);
        
        stage.setScene(scene);
        stage.setTitle("Jar");
        stage.show();
    }
    
    private static TreeView<Path> createTreeView(Path rootPath) {
        JarTreeItem rootItem = new JarTreeItem(rootPath);
        rootItem.setExpanded(true);
        
        TreeView<Path> tree = new TreeView<>(rootItem);
        tree.setMinWidth(200);
        
        return tree;
    }
    
}
