package com.github.zxh.classpy.helper;

import java.net.URL;

public class UrlHelper {
    

    
}

public fun getFileName(url: URL): String {
    val urlStr = url.toString()
    val idxOfDot = urlStr.lastIndexOf('/')
    return if (idxOfDot < 0) {
        urlStr
    } else {
        urlStr.substring(idxOfDot + 1)
    }

}