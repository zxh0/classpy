package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.body.ids.FieldIdItem;

/**
 *
 * @author zxh
 */
public class UIntFieldIdIndex extends UInt {

    @Override
    protected void postRead(DexFile dexFile) {
        int index = getValue();
        if (index >= 0) {
            FieldIdItem fieldId = dexFile.getFieldIdItem(index);
            String fieldName = fieldId.getDesc();
            String className = dexFile.getTypeIdItem(fieldId.getClassIdx()).getDesc();

            setDesc(index + "->" + className + "." + fieldName);
        }
    }
    
}
