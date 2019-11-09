package com.github.zxh.classpy.gui.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class ZipTreeLoader {

    public static ZipTreeNode load(File zipFile) throws Exception {
        try (FileSystem zipFS = FileSystems.newFileSystem(zipFile.toPath(), null)) {
            return path2node(zipFS.getPath("/"));
        }
    }

    private static ZipTreeNode path2node(Path path) throws IOException {
        ZipTreeNode node = new ZipTreeNode(path);

        Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1,
                new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path subPath, BasicFileAttributes attrs) throws IOException {
                        if (Files.isDirectory(subPath)) {
                            ZipTreeNode subNode = path2node(subPath);
                            if (subNode.hasSubNodes()) {
                                node.addSubNode(subNode);
                            }
                        } else if (isClassFile(subPath)) {
                            node.addSubNode(new ZipTreeNode(subPath));
                        }

                        return FileVisitResult.CONTINUE;
                    }

                });

        node.sortSubNodes();
        return node;
    }

    private static boolean isClassFile(Path p) {
        return p.toString().endsWith(".class");
    }

}
