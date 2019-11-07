package com.github.zxh.classpy.gui.support;

import java.net.MalformedURLException;

/**
 * Recent open file.
 */
public class RecentFile {

    public final FileType type;
    public final String url;

    public RecentFile(FileType type, String url) {
        this.type = type;
        this.url = url;
    }

    public RecentFile(String str) throws MalformedURLException {
        this(FileType.valueOf(str.split("#=>")[0]), str.split("#=>")[1]);
    }

    @Override
    public String toString() {
        return type + "#=>" + url;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof  RecentFile)
                && ((RecentFile) o).url.equals(this.url);
    }

}
