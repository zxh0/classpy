package com.github.zxh.classpy.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public abstract class BytesReader {
    
    protected final ByteBuffer buf;

    public BytesReader(byte[] bytes, ByteOrder order) {
        buf = ByteBuffer.wrap(bytes)
                .order(order)
                .asReadOnlyBuffer();
    }
    
    public ByteBuffer getByteBuffer() {
        return buf;
    }
    
    public int getPosition() {
        return buf.position();
    }
    
    public void skipBytes(int n) {
        for (int i = 0; i < n; i++) {
            buf.get();
        }
    }
    
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        buf.get(bytes);
        return bytes;
    }
    
}
