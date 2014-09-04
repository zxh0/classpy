package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 * Signed Little-Endian Base 128.
 *
 * @author zxh
 */
public class Sleb128 extends DexComponent implements IntValue {

    private int value;
    
    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        for (int i = 0; i < 5; i++) {
            final byte b = reader.readByte();
            
            if (b >= 0) {
                // most significant bit clear
                value |= (((b << 25) >> 25) << (i * 7));
                break;
            } else {
                value |= ((b & 0b0111_1111) << (i * 7));
            }
        }
        
        describe(value);
    }
    
    protected void describe(int value) {
        setDesc(value);
    }
    
}
