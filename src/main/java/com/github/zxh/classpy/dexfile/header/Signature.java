package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 * ubyte[20].
 *
 * @author zxh
 */
public class Signature extends DexComponent {

    @Override
    protected void readContent(DexReader reader) {
        StringBuilder signature = new StringBuilder("0x");
        for (int i = 0; i < 20; i++) {
            signature.append(Integer.toHexString(reader.readUByte()));
        }
        
        setDesc(signature.toString());
    }
    
}
