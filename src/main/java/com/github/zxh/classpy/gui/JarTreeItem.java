package com.github.zxh.classpy.gui;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class JarTreeItem extends TreeItem<Path> {

    private boolean isFirstTimeChildren = true;
    
    public JarTreeItem(Path root) {
        super(root);
    }

    @Override
    public boolean isLeaf() {
        return !getValue().toString().endsWith("/");
    }
    
    @Override
    public ObservableList<TreeItem<Path>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            System.out.println("get children of " + getValue());

            // First getChildren() call, so we actually go off and 
            // determine the children of the File contained in this TreeItem.
            super.getChildren().setAll(buildChildren());
        }
        
        return super.getChildren();
    }

    private ObservableList<TreeItem<Path>> buildChildren() {
        ObservableList<TreeItem<Path>> children = FXCollections.observableArrayList();
        
        try {
            Files.walkFileTree(getValue(), EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<Path>() {  

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {  
                    children.add(new JarTreeItem(file));

                    return FileVisitResult.CONTINUE;  
                }  

            });  
        } catch (IOException e) {
            // todo
            e.printStackTrace(System.err);
        }
        
        return children;
    }
    
}
