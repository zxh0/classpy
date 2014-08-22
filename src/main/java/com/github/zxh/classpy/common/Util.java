package com.github.zxh.classpy.common;

/**
 *
 * @author zxh
 */
public class Util {
    
    /**
     * Convert index to String.
     * Examples:
     * maxIndex index result
     *  9         8    #8
     * 15         8    #08
     * 15        12    #12
     * 123        8    #008
     * 123       12    #012
     * 123      120    #120
     * 
     * @param maxIndex
     * @param index
     * @return 
     */
    public static String formatIndex(int maxIndex, int index) {
        int idxWidth = String.valueOf(maxIndex).length();
        String fmtStr = "#%0" + idxWidth + "d";
        return String.format(fmtStr, index);
    }
    
}
