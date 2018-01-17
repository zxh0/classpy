package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.classfile.ClassFileParser;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.gui.jar.JarTreeLoader;
import com.github.zxh.classpy.gui.jar.JarTreeNode;
import com.github.zxh.classpy.gui.parsed.HexText;
import com.github.zxh.classpy.helper.UrlHelper;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkParser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.function.Consumer;

import javafx.concurrent.Task;

public class OpenFileTask extends Task<OpenFileResult> {

    private static final byte[] classMagicNumber = {(byte)0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};
    private static final byte[] binaryChunkSig = {0x1B, 'L', 'u', 'a'};


    private final URL url;

    public OpenFileTask(URL url) {
        this.url = url;
    }
    
    @Override
    protected OpenFileResult call() throws Exception {
        System.out.println("loading " + url + "...");

        FileType fileType = getFileType(url);
        if (fileType == FileType.JAVA_JAR) {
            JarTreeNode rootNode = JarTreeLoader.load(new File(url.toURI()));
            return new OpenFileResult(url, fileType, rootNode);
        }

        byte[] data = UrlHelper.readData(url);
        if (fileType == FileType.UNKNOWN) {
            fileType = getFileType(data);
        }

        HexText hex = new HexText(data);
        FileComponent fc = parse(data, fileType);
        fc.setName(UrlHelper.getFileName(url));

        System.out.println("finish loading");
        return new OpenFileResult(url, fileType, fc, hex);
    }

    private static FileType getFileType(URL url) {
        String filename = url.toString().toLowerCase();
        if (filename.endsWith(".jar")) {
            return FileType.JAVA_JAR;
        }
        if (filename.endsWith(".class")) {
            return FileType.JAVA_CLASS;
        }
        if (filename.endsWith(".luac")) {
            return FileType.LUA_BC;
        }
        return FileType.UNKNOWN;
    }

    private static FileType getFileType(byte[] data) {
        if (data.length >= 4) {
            byte[] magicNumber = Arrays.copyOf(data, 4);
            if (Arrays.equals(magicNumber, classMagicNumber)) {
                return FileType.JAVA_CLASS;
            }
            if (Arrays.equals(magicNumber, binaryChunkSig)) {
                return FileType.LUA_BC;
            }
        }
        return FileType.UNKNOWN;
    }

    private static FileComponent parse(byte[] data, FileType fileType) {
        switch (fileType) {
            case JAVA_CLASS: return new ClassFileParser().parse(data);
            case LUA_BC: return new BinaryChunkParser().parse(data);
            default: return new FileComponent() {}; // todo
        }
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
