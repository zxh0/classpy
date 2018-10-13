package com.github.zxh.classpy.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlHelper {

    public static String readOneLine(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return reader.readLine();
        }
    }

    public static byte[] readData(URL url) throws IOException {
        try (InputStream is = url.openStream()) {
            byte[] data = new byte[is.available()];
            int len = 0;
            while (len < data.length) {
                len += is.read(data, len, data.length - len);
            }
            return data;
        }
    }

    public static String getFileName(URL url) {
        String urlStr = url.toString();
        int idxOfDot = urlStr.lastIndexOf('/');
        return idxOfDot < 0 ? urlStr : urlStr.substring(idxOfDot + 1);
    }
    
}
