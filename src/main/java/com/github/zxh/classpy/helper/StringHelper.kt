package com.github.zxh.classpy.helper;

object StringHelper {
    
    /**
     * Convert i to HEX string.
     * 
     * @param i
     * @return 
     */
    @JvmStatic fun toHexString(i: Int): String {
        return "0x" + Integer.toHexString(i).toUpperCase()
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
    @JvmStatic fun formatIndex(maxIndex: Int, index: Int): String {
        val idxWidth = maxIndex.toString().length
        val fmtStr = "#%0" + idxWidth + "d"
        return String.format(fmtStr, index)
    }
    
    /**
     * Cut the string and append ellipsis.
     * @param str
     * @param maxLength
     * @return 
     */
    @JvmStatic fun cutAndAppendEllipsis(str: String, maxLength: Int): String {
        val str = str.replace("\\r|\\n".toRegex(), "")
        
        if (str.length <= maxLength) {
            return str;
        }
        
        val cutPos = maxLength - 3
        val firstCutChar = str[cutPos]
        
        if (Character.isLowSurrogate(firstCutChar)) {
            return str.substring(0, cutPos - 1) + "..."
        } else {
            return str.substring(0, cutPos) + "..."
        }
    }
    
}
