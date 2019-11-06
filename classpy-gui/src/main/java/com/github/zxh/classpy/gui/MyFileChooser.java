package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.gui.support.FileType;
import com.github.zxh.classpy.gui.support.RecentFiles;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MyFileChooser {

    private static FileChooser fileChooser;
    private static DirectoryChooser dirChooser;

    public static File showFileChooser(Stage stage, FileType ft) {
        if (fileChooser == null) {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
        }

        File lastOpenFile = RecentFiles.INSTANCE.getLastOpenFile(ft);
        if (lastOpenFile != null && lastOpenFile.getParentFile().isDirectory()) {
            fileChooser.setInitialDirectory(lastOpenFile.getParentFile());
        }

        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(ft.filter);

        return fileChooser.showOpenDialog(stage);
    }

    public static File showDirChooser(Stage stage) {
        if (dirChooser == null) {
            dirChooser = new DirectoryChooser();
            dirChooser.setTitle("Open folder");
        }

        return dirChooser.showDialog(stage);
    }

}
