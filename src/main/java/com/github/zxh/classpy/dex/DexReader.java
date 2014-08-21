package com.github.zxh.classpy.dex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public class DexReader {
    
    private final ByteBuffer buf;

    public DexReader(byte[] bytes) {
        this.buf = ByteBuffer.wrap(bytes);
        this.buf.order(ByteOrder.LITTLE_ENDIAN);
    }
    
}
