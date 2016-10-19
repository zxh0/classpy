package com.github.zxh.classpy.gui.support;

import com.github.zxh.classpy.classfile.ClassParser;
import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.helper.UrlHelper;
import com.github.zxh.classpy.luacout.LuacOutParser;
import java.io.InputStream;
import java.net.URL;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.concurrent.Task;

public class OpenFileTask extends Task<Object> {

    private final URL url;

    public OpenFileTask(URL url) {
        this.url = url;
    }
    
    @Override
    protected Object call() throws Exception {
        System.out.println("loading " + url + "...");
        
        try (InputStream is = url.openStream()) {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            BytesComponent cc = parse(bytes);
            cc.setName(UrlHelper.getFileName(url));
            HexText hex = new HexText(bytes);

            System.out.println("finish loading");
            return new Object[] {cc, hex};
        }
    }

    private BytesComponent parse(byte[] bytes) {
        if (url.toString().endsWith(".class")) {
            return new ClassParser().parse(bytes);
        } else {
            // todo
            return new LuacOutParser().parse(bytes);
        }
    }
    
    public void setOnSucceeded(BiConsumer<BytesComponent, HexText> callback) {
        super.setOnSucceeded(e -> {
            Object[] arr = (Object[]) e.getSource().getValue();
            BytesComponent cc = (BytesComponent) arr[0];
            HexText hex = (HexText) arr[1];
            
            callback.accept(cc, hex);
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
