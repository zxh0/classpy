package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.gui.parsed.HexText;
import com.github.zxh.classpy.gui.fs.ZipTreeNode;

public class OpenFileResult {

    public final String url;
    public final FileType fileType;
    public final ZipTreeNode zipRootNode;
    public final FilePart fileRootNode;
    public final HexText hexText;

    public OpenFileResult(String url, FileType fileType,
                          ZipTreeNode zipRootNode) {
        this.url = url;
        this.fileType = fileType;
        this.zipRootNode = zipRootNode;
        this.fileRootNode = null;
        this.hexText = null;
    }

    public OpenFileResult(String url, FileType fileType,
                          FilePart fileRootNode, HexText hexText) {
        this.url = url;
        this.fileType = fileType;
        this.zipRootNode = null;
        this.fileRootNode = fileRootNode;
        this.hexText = hexText;
    }

}
