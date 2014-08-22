package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassParser;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import com.github.zxh.classpy.dexfile.DexParser;
import java.io.File;
import java.nio.file.Files;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.concurrent.Task;

/**
 *
 * @author zxh
 */
public class OpenFileTask extends Task<Object> {

    private final File file;

    public OpenFileTask(File file) {
        this.file = file;
    }
    
    @Override
    protected Object call() throws Exception {
        System.out.println("loading " + file.getAbsolutePath() + "...");
        
        byte[] bytes = Files.readAllBytes(file.toPath());
        FileHex hex = new FileHex(bytes);
        FileComponent fc = file.getName().endsWith(".class")
                ? ClassParser.parse(bytes)
                : DexParser.parse(bytes);

        System.out.println("finish loading");
        return new Object[] {hex, fc};
    }
    
    public void setOnSucceeded(BiConsumer<FileComponent, FileHex> callback) {
        super.setOnSucceeded(e -> {
            Object[] arr = (Object[]) e.getSource().getValue();
            FileHex hex = (FileHex) arr[0];
            FileComponent fc = (FileComponent) arr[1];
            
            callback.accept(fc, hex);
        });
    }
    
    public void setOnFailed(Consumer<Throwable> callback) {
        super.setOnFailed(e -> {
            Throwable err = e.getSource().getException();
            err.printStackTrace(System.err);
            
            callback.accept(err);
        });
    }
    
    public void startInNewThread() {
        new Thread(this).start();
    }
    
}
