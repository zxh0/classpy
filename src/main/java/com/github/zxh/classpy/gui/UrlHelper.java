package com.github.zxh.classpy.gui;

import java.net.URL;

public class UrlHelper {
    
    public static String getFileName(URL url) {
        String urlStr = url.toString();
        int idxOfDot = urlStr.lastIndexOf('/');
        return idxOfDot < 0 ? urlStr : urlStr.substring(idxOfDot + 1);
    }
    
}
