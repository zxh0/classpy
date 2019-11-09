package com.github.zxh.classpy.gui.fs;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class DirTreeNode extends BaseTreeNode {

    public DirTreeNode(Path path) {
        super(path);
    }

    boolean hasSubNodes() {
        return super.isDir();
    }

    List<DirTreeNode> getSubNodes() {
        List<DirTreeNode> nodes = new ArrayList<>();

        try {
            Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1,
                    new SimpleFileVisitor<Path>() {

                        @Override
                        public FileVisitResult visitFile(Path subPath, BasicFileAttributes attrs) {
                            if (!subPath.getFileName().toString().startsWith(".")) {
                                nodes.add(new DirTreeNode(subPath));
                            }
                            return FileVisitResult.CONTINUE;
                        }

                    });
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        Collections.sort(nodes);
        return nodes;
    }

}
