package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 *
 * @author zxh
 */
public class UIntHex extends DexComponent {

    private int value;

    public int getValue() {
        if (value < 0) {
            // todo
        }
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        value = reader.readInt(); // todo
        setDesc("0x" + Integer.toHexString(value));
    }
    
}
