package com.github.zxh.classpy.gui.jar;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JarTreeNode {

    final String path;
    final String name;
    List<JarTreeNode> subNodes;

    JarTreeNode(Path path) {
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

    void addSubNode(JarTreeNode node) {
        if (subNodes == null) {
            subNodes = new ArrayList<>();
        }
        subNodes.add(node);
    }

    void sortSubNodes() {
        if (subNodes != null) {
            subNodes.sort(JarTreeNode::comparePaths);
        }
    }

    static int comparePaths(JarTreeNode n1, JarTreeNode n2) {
        if (n1.hasSubNodes() && !n2.hasSubNodes()) {
            return -1;
        } else if (!n1.hasSubNodes() && n2.hasSubNodes()) {
            return 1;
        } else {
            return n1.name.compareTo(n2.name);
        }
    }

}
