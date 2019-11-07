package com.github.zxh.classpy.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlHelper {

    public static String readOneLine(String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            return reader.readLine();
        }
    }

    public static byte[] readData(String url) throws IOException {
        if (url.startsWith("jmod:")) {
            url = url.replace("jmod:", "jar:");
        }
        try (InputStream is = new URL(url).openStream()) {
            byte[] data = new byte[is.available()];
            int len = 0;
            while (len < data.length) {
                len += is.read(data, len, data.length - len);
            }
            return data;
        }
    }

    public static String getFileName(String url) {
        int lastSlashIdx = url.lastIndexOf('/');
        return lastSlashIdx < 0 ? url : url.substring(lastSlashIdx + 1);
    }

    public static String getFileSuffix(String url) {
        String fileName = getFileName(url);
        int lastDotIdx = fileName.lastIndexOf('.');
        return lastDotIdx < 0 ? "" : fileName.substring(lastDotIdx + 1);
    }

}
