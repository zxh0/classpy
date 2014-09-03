package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.body.ids.TypeIdItem;

/**
 *
 * @author zxh
 */
public class UIntTypeIdIndex extends UInt {

    @Override
    protected void postRead(DexFile dexFile) {
        int index = getValue();
        if (index >= 0) {
            TypeIdItem typeId = dexFile.getTypeIdItem(index);
            String typeDesc = dexFile.getString(typeId.getDescriptorIdx());

            setDesc(index + "->" + typeDesc);
        }
    }
    
}
