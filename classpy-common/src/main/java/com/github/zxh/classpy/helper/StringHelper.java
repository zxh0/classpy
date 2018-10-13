package com.github.zxh.classpy.helper;

public class StringHelper {
    
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

    /**
     * Convert hex string to byte array.
     * @param str
     * @return
     */
    public static byte[] hex2Bytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(
                    str.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}
