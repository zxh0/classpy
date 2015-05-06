package com.github.zxh.classpy.classfile.helper;

/**
 *
 * @author zxh
 */
public class StringUtil {
    
    /**
     * Convert i to HEX string.
     * 
     * @param i
     * @return 
     */
    public static String toHexString(int i) {
        return "0x" + Integer.toHexString(i);
    }
    
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
     * Cut the string and append ellipsis.
     * @param str
     * @param maxLength
     * @return 
     */
    public static String cutAndAppendEllipsis(String str, int maxLength) {
        str = str.replaceAll("\\r|\\n", "");
        
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
