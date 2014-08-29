package com.github.zxh.classpy.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public abstract class BytesReader {
    
    protected final ByteBuffer buf;

    public BytesReader(byte[] bytes, ByteOrder order, boolean readOnly) {
        ByteBuffer _buf = ByteBuffer.wrap(bytes).order(order);
        this.buf = readOnly ? _buf.asReadOnlyBuffer() : _buf;
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
    
    // byte[]
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        buf.get(bytes);
        return bytes;
    }
    
    // 8-bit signed int
    public byte readByte() {
        return buf.get();
    }
    
    // 8-bit unsigned int
    public int readUByte() {
        return Byte.toUnsignedInt(buf.get());
    }
    
}
