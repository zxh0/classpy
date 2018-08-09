package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.gui.jar.JarTreeNode;
import com.github.zxh.classpy.gui.parsed.HexText;

import java.net.URL;

public class OpenFileResult {

    public final URL url;
    public final FileType fileType;
    public final JarTreeNode jarRootNode;
    public final FileComponent fileRootNode;
    public final HexText hexText;

    public OpenFileResult(URL url, FileType fileType,
                          JarTreeNode jarTreeNode) {
        this.url = url;
        this.fileType = fileType;
        this.jarRootNode = jarTreeNode;
        this.fileRootNode = null;
        this.hexText = null;
    }

    public OpenFileResult(URL url, FileType fileType,
                          FileComponent fileRootNode, HexText hexText) {
        this.url = url;
        this.fileType = fileType;
        this.jarRootNode = null;
        this.fileRootNode = fileRootNode;
        this.hexText = hexText;
    }

}
