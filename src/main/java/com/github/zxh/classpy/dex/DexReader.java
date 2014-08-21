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
    
    // 8-bit signed int
    public byte readByte() {
        return buf.get();
    }
    
    // 8-bit unsigned int
    public int readUByte() {
        return Byte.toUnsignedInt(buf.get());
    }
    
    // 16-bit signed int, little-endian
    public short readShort() {
        return buf.getShort();
    }
    
    // 16-bit unsigned int, little-endian
    public int readUShort() {
        return Short.toUnsignedInt(buf.getShort());
    }
    
    // 32-bit signed int, little-endian
    public int readInt() {
        return buf.getInt();
    }
    
    // 32-bit unsigned int, little-endian
    public long readUInt() {
        return Integer.toUnsignedLong(buf.getInt());
    }
    
    // 64-bit signed int, little-endian
    public long readLong() {
        return buf.getLong();
    }
    
    // 64-bit unsigned int, little-endian
    public long readULong() {
        long ulong = buf.getLong();
        if (ulong < 0) {
            // todo
        }
        
        return ulong;
    }
    
}
