package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 *
 * @author zxh
 */
public class UShort extends DexComponent {

    private int value;

    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        value = Short.toUnsignedInt(reader.readShort());
        setDesc(String.valueOf(value));
    }
    
}
