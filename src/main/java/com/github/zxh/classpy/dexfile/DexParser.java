package com.github.zxh.classpy.dexfile;

/**
 *
 * @author zxh
 */
public class DexParser {

    // todo
    public static DexFile parse(byte[] bytes) {
        DexFile dex = new DexFile();
        dex.read(new DexReader(bytes));
        //cf.setBytes(bytes);
        return dex;
    }
    
}
