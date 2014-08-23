package com.github.zxh.classpy.dexfile;

/**
 * Unsigned Little-Endian Base 128.
 *
 * @author zxh
 */
public class ULEB128 extends DexComponent {

    private int value;
    
    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        for (int i = 0; i < 5; i++) {
            final byte b = reader.readByte();
            
            value |= ((b & 0b0111_1111) << (i * 7));
            
            if ((b >> 7) == 0) {
                break;
            }
        }
    }
    
}
