package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.ids.TypeIdItem;

/**
 *
 * @author zxh
 */
public class UShortTypeIdIndex extends UShort {

    @Override
    protected void postRead(DexFile dexFile) {
        TypeIdItem typeId = dexFile.getTypeIdItem(getValue());
        String typeDesc = dexFile.getString(typeId.getDescriptorIdx());
        
        setDesc(getValue() + "->" + typeDesc);
    }
    
}
