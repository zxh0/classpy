package com.github.zxh.classpy.gui;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
            TreeView<Path> jarTree = createTreeView(rootPath);
            Button openButton = new Button("Open");
            openButton.setOnAction(e -> stage.close());
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> stage.close());
            
            BorderPane rootPane = createRootPane(jarTree, openButton, cancelButton);
            Scene scene = new Scene(rootPane, 500, 300);
            
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
    
    private static BorderPane createRootPane(TreeView<Path> jarTree,
            Button openButton, Button cancelButton) {
        
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(openButton);
        buttonBox.getChildren().add(cancelButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(4, 4, 4, 4));
        buttonBox.setSpacing(4);
        
        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(jarTree);
        rootPane.setBottom(buttonBox);
        return rootPane;
    }
    
}
