package com.github.zxh.classpy.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlHelper {

    public static byte[] readData(URL url) throws IOException {
        try (InputStream is = url.openStream()) {
            byte[] data = new byte[is.available()];
            is.read(data);
            return data;
        }
    }

    public static String getFileName(URL url) {
        String urlStr = url.toString();
        int idxOfDot = urlStr.lastIndexOf('/');
        return idxOfDot < 0 ? urlStr : urlStr.substring(idxOfDot + 1);
    }
    
}
