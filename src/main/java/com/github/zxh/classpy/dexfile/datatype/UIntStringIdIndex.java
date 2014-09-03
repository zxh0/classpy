package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexFile;

/**
 *
 * @author zxh
 */
public class UIntStringIdIndex extends UInt {

    @Override
    protected void postRead(DexFile dexFile) {
        int index = getValue();
        if (index >= 0) {
            setDesc(index + "->" + dexFile.getString(index));
        }
    }
    
}
