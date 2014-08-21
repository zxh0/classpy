package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.common.FileComponentHelper;

/**
 *
 * @author zxh
 */
public class DexParser {

    // todo
    public static DexFile parse(byte[] bytes) {
        DexFile dex = new DexFile();
        dex.read(new DexReader(bytes));
        
        try {
            FileComponentHelper.inferSubComponentName(dex);
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return dex;
    }
    
}
