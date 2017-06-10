package com.github.zxh.classpy.gui;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import sun.java2d.pipe.SpanShapeRenderer;

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
        
        getSubPaths(getValue()).stream()
                .filter(path -> isFolder(path) || isClassFile(path))
                .sorted((p1, p2) -> comparePaths(p1, p2))
                .forEach(path -> children.add(new JarTreeItem(path)));
        
        return children;
    }
    
    
    private static List<Path> getSubPaths(Path pathInJar) {
        List<Path> subPaths = new ArrayList<>();
        
        try {
            Files.walkFileTree(pathInJar, EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    subPaths.add(file);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace(System.err); // todo
        }
        
        return subPaths;
    }
    
    private static boolean isFolder(Path p) {
        return p.toString().endsWith("/");
    }
    
    private static boolean isClassFile(Path p) {
        return p.toString().endsWith(".class");
    }
    
    private int comparePaths(Path p1, Path p2) {
        if (isFolder(p1) && isClassFile(p2)) {
            return -1;
        } else if (isClassFile(p1) && isFolder(p2)) {
            return 1;
        } else {
            return p1.toString().compareTo(p2.toString());
        }
    }

}
