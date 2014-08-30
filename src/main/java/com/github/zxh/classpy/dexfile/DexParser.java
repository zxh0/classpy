package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class DexParser implements FileParser {

    @Override
    public DexFile parse(byte[] bytes) {
        DexFile dex = new DexFile();
        dex.read(new DexReader(bytes));
        return dex;
    }
    
}
