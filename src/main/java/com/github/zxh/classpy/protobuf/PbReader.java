package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.common.BytesReader;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public class PbReader extends BytesReader {

    public PbReader(byte[] bytes) {
        super(bytes, ByteOrder.LITTLE_ENDIAN, true);
    }
    
    // Base 128 Varints
    public int readVarint() {
        int value = 0;
        
        for (int i = 0; i < 5; i++) {
            final byte b = readByte();
            
            value |= ((b & 0b0111_1111) << (i * 7));
            
            if (b >= 0) {
                // most significant bit clear
                break;
            }
        }
        
        return value;
    }
    
}
