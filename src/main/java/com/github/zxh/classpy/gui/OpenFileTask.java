package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.FileParser;
import com.github.zxh.classpy.common.FileParsers;
import java.io.InputStream;
import java.net.URL;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.concurrent.Task;

/**
 *
 * @author zxh
 */
public class OpenFileTask extends Task<Object> {

    private final URL url;

    public OpenFileTask(URL url) {
        this.url = url;
    }
    
    @Override
    protected Object call() throws Exception {
        System.out.println("loading " + url + "...");
        
        try (InputStream is = url.openStream()) {
            if (is.available() > 512 * 1024) {
                throw new FileParseException("File is too large!");
            }

            String fileType = UrlHelper.getExtension(url);
            FileParser parser = FileParsers.getParser(fileType);

            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            FileComponent fc = parser.parse(bytes);
            fc.setName(UrlHelper.getFileName(url));
            FileHex hex = new FileHex(bytes);

            System.out.println("finish loading");
            return new Object[] {fc, hex};
        }
    }
    
    public void setOnSucceeded(BiConsumer<FileComponent, FileHex> callback) {
        super.setOnSucceeded(e -> {
            Object[] arr = (Object[]) e.getSource().getValue();
            FileComponent fc = (FileComponent) arr[0];
            FileHex hex = (FileHex) arr[1];
            
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
