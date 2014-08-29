package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.FileComponentHelper;
import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class DexParser implements FileParser {

    @Override
    public DexFile parse(byte[] bytes) throws FileParseException {
        DexFile dex = new DexFile();
        dex.read(new DexReader(bytes));
        
        try {
            FileComponentHelper.inferSubComponentName(dex);
        } catch (ReflectiveOperationException e) {
            throw new FileParseException(e);
        }
        
        return dex;
    }
    
}
