package com.github.zxh.classpy.gui.events;

import com.github.zxh.classpy.gui.support.FileType;

public class OpenFile {

    public final FileType fileType;
    public final String fileUrl;

    public OpenFile(FileType fileType, String fileUrl) {
        this.fileType = fileType;
        this.fileUrl = fileUrl;
    }

}
