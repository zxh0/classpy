package com.github.zxh.classpy.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BytesReader {
    
    private final ByteBuffer buf;

    public BytesReader(byte[] data, ByteOrder order) {
        this.buf = ByteBuffer.wrap(data)
                .asReadOnlyBuffer()
                .order(order);
    }

    public int remaining() {
        return buf.remaining();
    }

    public int getPosition() {
        return buf.position();
    }

    public byte getByte(int index) {
        return buf.get(index);
    }

    public short getShort(int index) {
        return buf.getShort(index);
    }

    // 8-bit signed int
    public byte readByte() {
        return buf.get();
    }
    
    // 8-bit unsigned int
    public int readUnsignedByte() {
        return Byte.toUnsignedInt(buf.get());
    }
    
    // 16-bit signed int
    public short readShort() {
        return buf.getShort();
    }
    
    // 16-bit unsigned int
    public int readUnsignedShort() {
        return Short.toUnsignedInt(buf.getShort());
    }
    
    // 32-bit signed int
    public int readInt() {
        return buf.getInt();
    }

    // 32-bit unsigned int
    public long readUnsignedInt() {
        return Integer.toUnsignedLong(buf.getInt());
    }

    // 64-bit signed int
    public long readLong() {
        return buf.getLong();
    }

    public float readFloat() {
        return buf.getFloat();
    }

    public double readDouble() {
        return buf.getDouble();
    }

    // byte[]
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        buf.get(bytes);
        return bytes;
    }

    public void skipBytes(int n) {
        for (int i = 0; i < n; i++) {
            buf.get();
        }
    }

}
