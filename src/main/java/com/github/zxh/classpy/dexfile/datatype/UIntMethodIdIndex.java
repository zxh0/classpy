package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.body.ids.MethodIdItem;

/**
 *
 * @author zxh
 */
public class UIntMethodIdIndex extends UInt {

    @Override
    protected void postRead(DexFile dexFile) {
        MethodIdItem methodId = dexFile.getMethodIdItem(getValue());
        String methodName = methodId.getDesc();
        String className = dexFile.getTypeIdItem(methodId.getClassIdx()).getDesc();
        
        setDesc(getValue() + "->" + className + "." + methodName);
    }
    
}
