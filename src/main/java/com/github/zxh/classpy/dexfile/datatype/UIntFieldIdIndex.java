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
        FieldIdItem fieldId = dexFile.getFieldIdItem(getValue());
        String fieldName = fieldId.getDesc();
        String className = dexFile.getTypeIdItem(fieldId.getClassIdx()).getDesc();
        
        setDesc(getValue() + "->" + className + "." + fieldName);
    }
    
}
