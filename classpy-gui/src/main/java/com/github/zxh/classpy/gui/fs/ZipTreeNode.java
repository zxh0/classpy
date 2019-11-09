package com.github.zxh.classpy.gui.fs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ZipTreeNode {

    final String path;
    final String name;
    List<ZipTreeNode> subNodes;

    ZipTreeNode(Path path) {
        this.path = path.toString();
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
        return subNodes != null && !subNodes.isEmpty();
    }

    void addSubNode(ZipTreeNode node) {
        if (subNodes == null) {
            subNodes = new ArrayList<>();
        }
        subNodes.add(node);
    }

    void sortSubNodes() {
        if (subNodes != null) {
            subNodes.sort(ZipTreeNode::comparePaths);
        }
    }

    private static int comparePaths(ZipTreeNode n1, ZipTreeNode n2) {
        if (n1.hasSubNodes() && !n2.hasSubNodes()) {
            return -1;
        } else if (!n1.hasSubNodes() && n2.hasSubNodes()) {
            return 1;
        } else {
            return n1.name.compareTo(n2.name);
        }
    }

}
