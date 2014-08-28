package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 * Unsigned Little-Endian Base 128.
 *
 * @author zxh
 */
public class Uleb128 extends DexComponent implements IntValue {

    private int value;
    
    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        for (int i = 0; i < 5; i++) {
            final byte b = reader.readByte();
            
            value |= ((b & 0b0111_1111) << (i * 7));
            
            if (b >= 0) {
                // most significant bit clear
                break;
            }
        }
        
        describe(value);
    }
    
    protected void describe(int value) {
        setDesc(String.valueOf(value));
    }
    
}
