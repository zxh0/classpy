package com.github.zxh.classpy.helper

import java.net.URL

object UrlHelper {

    @JvmStatic
    fun getFileName(url: URL): String {
        val urlStr = url.toString()
        val idxOfDot = urlStr.lastIndexOf('/')
        return if (idxOfDot < 0) urlStr else urlStr.substring(idxOfDot + 1)
    }

}
