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
     *   9        8    #8
     *  15        8    #08
     *  15       12    #12
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
    
    /**
     * Cut the string and append ellipsis if it is too long.
     * @param str
     * @param maxLength
     * @return 
     */
    public static String cutAndAppendEllipsis(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        
        int cutPos = maxLength - 3;
        char firstCutChar = str.charAt(cutPos);
        
        if (Character.isLowSurrogate(firstCutChar)) {
            return str.substring(0, cutPos - 1) + "...";
        } else {
            return str.substring(0, cutPos) + "...";
        }
    }
    
}
