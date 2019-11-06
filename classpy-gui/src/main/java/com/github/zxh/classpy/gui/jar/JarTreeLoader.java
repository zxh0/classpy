package com.github.zxh.classpy.gui.jar;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class JarTreeLoader {

    public static JarTreeNode load(File jarFile) throws Exception {
        try (FileSystem zipFs = FileSystems.newFileSystem(jarFile.toPath(), null)) {
            return path2node(zipFs.getPath("/"));
        }
    }

    private static JarTreeNode path2node(Path path) throws IOException {
        JarTreeNode node = new JarTreeNode(path);

        Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1,
                new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path subPath, BasicFileAttributes attrs) throws IOException {
                        if (Files.isDirectory(subPath)) {
                            JarTreeNode subNode = path2node(subPath);
                            if (subNode.hasSubNodes()) {
                                node.addSubNode(subNode);
                            }
                        } else if (isClassFile(subPath)) {
                            node.addSubNode(new JarTreeNode(subPath));
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
