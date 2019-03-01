package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.gui.jar.JarTreeLoader;
import com.github.zxh.classpy.gui.jar.JarTreeNode;
import com.github.zxh.classpy.gui.parsed.HexText;
import com.github.zxh.classpy.helper.StringHelper;
import com.github.zxh.classpy.helper.UrlHelper;

import java.io.File;
import java.net.URL;
import java.util.function.Consumer;

import javafx.concurrent.Task;

public class OpenFileTask extends Task<OpenFileResult> {

    private final URL url;

    public OpenFileTask(URL url) {
        this.url = url;
    }

    @Override
    protected OpenFileResult call() throws Exception {
        System.out.println("loading " + url + "...");

        FileType fileType = FileTypeInferer.inferFileType(url);
        if (fileType == FileType.JAVA_JAR) {
            JarTreeNode rootNode = JarTreeLoader.load(new File(url.toURI()));
            return new OpenFileResult(url, fileType, rootNode);
        }

        byte[] data = (fileType == FileType.BITCOIN_BLOCK || fileType == FileType.BITCOIN_TX)
                ? StringHelper.hex2Bytes(UrlHelper.readOneLine(url))
                : UrlHelper.readData(url);
        if (fileType == FileType.UNKNOWN) {
            fileType = FileTypeInferer.inferFileType(data);
        }

        System.out.println("parsing " + url + "...");
        FilePart fc = fileType.parser.parse(data);
        fc.setName(UrlHelper.getFileName(url));
        HexText hex = new HexText(data);

        System.out.println("finish loading");
        return new OpenFileResult(url, fileType, fc, hex);
    }

    public void setOnSucceeded(Consumer<OpenFileResult> callback) {
        super.setOnSucceeded(
                e -> callback.accept((OpenFileResult) e.getSource().getValue()));
    }

    public void setOnFailed(Consumer<Throwable> callback) {
        super.setOnFailed(event -> {
            Throwable err = event.getSource().getException();
            err.printStackTrace(System.err);

            callback.accept(err);
        });
    }

    public void startInNewThread() {
        new Thread(this).start();
    }

}
