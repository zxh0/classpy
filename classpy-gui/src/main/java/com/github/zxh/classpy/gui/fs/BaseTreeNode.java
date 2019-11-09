package com.github.zxh.classpy.gui.fs;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class BaseTreeNode implements Comparable<BaseTreeNode> {

    final Path path;
    final String name;

    BaseTreeNode(Path path) {
        this.path = path;
        if (path.getFileName() != null) {
            this.name = path.getFileName().toString();
        } else {
            this.name = path.toString();
        }
    }

    boolean isDir() {
        return Files.isDirectory(path);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(BaseTreeNode that) {
        if (this.isDir() && !that.isDir()) {
            return -1;
        } else if (!this.isDir() && that.isDir()) {
            return 1;
        } else {
            return this.name.compareTo(that.name);
        }
    }

}
