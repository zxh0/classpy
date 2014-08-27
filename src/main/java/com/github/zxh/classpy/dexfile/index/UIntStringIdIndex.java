package com.github.zxh.classpy.dexfile.index;

import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.UInt;

/**
 *
 * @author zxh
 */
public class UIntStringIdIndex extends UInt {

    @Override
    protected void postRead(DexFile dexFile) {
        setDesc(getValue() + "->" + dexFile.getString(getValue()));
    }
    
}
