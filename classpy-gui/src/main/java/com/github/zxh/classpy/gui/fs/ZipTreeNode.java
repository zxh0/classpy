package com.github.zxh.classpy.gui.fs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZipTreeNode extends BaseTreeNode {

    List<ZipTreeNode> subNodes;

    public ZipTreeNode(Path path) {
        super(path);
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
            Collections.sort(subNodes);
        }
    }

}
