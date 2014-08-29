package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.common.BytesReader;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public class PeReader extends BytesReader {

    public PeReader(byte[] bytes) {
        super(bytes, ByteOrder.LITTLE_ENDIAN, true);
    }
    
}
