package com.github.zxh.classpy.dexfile;

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
    public UInt readUInt() {
        UInt uint = new UInt();
        uint.read(this);
        return uint;
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
