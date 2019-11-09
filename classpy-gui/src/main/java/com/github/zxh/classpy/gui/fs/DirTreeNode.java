package com.github.zxh.classpy.gui.fs;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DirTreeNode {

    final Path path;
    final String name;

    public DirTreeNode(Path path) {
        this.path = path;
        if (path.getFileName() != null) {
            this.name = path.getFileName().toString();
        } else {
            this.name = path.toString();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    boolean hasSubNodes() {
        return Files.isDirectory(path);
    }

    List<DirTreeNode> getSubNodes() {
        List<DirTreeNode> nodes = new ArrayList<>();

        try {
            Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1,
                    new SimpleFileVisitor<Path>() {

                        @Override
                        public FileVisitResult visitFile(Path subPath, BasicFileAttributes attrs) {
                            nodes.add(new DirTreeNode(subPath));
                            return FileVisitResult.CONTINUE;
                        }

                    });
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return nodes;
    }

}
