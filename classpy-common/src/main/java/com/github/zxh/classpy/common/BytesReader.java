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

    public void setLimit(int newLimit) {
        buf.limit(newLimit);
    }
    public void clearLimit() {
        buf.limit(buf.capacity());
    }

    public byte getFixedI8(int index) {
        return buf.get(index);
    }

    public short getFixedI16(int index) {
        return buf.getShort(index);
    }

    public byte readByte() {
        return readFixedI8();
    }

    // 8-bit signed int
    public byte readFixedI8() {
        return buf.get();
    }

    // 8-bit unsigned int
    public int readFixedU8() {
        return Byte.toUnsignedInt(buf.get());
    }

    // 16-bit signed int
    public short readFixedI16() {
        return buf.getShort();
    }

    // 16-bit unsigned int
    public int readFixedU16() {
        return Short.toUnsignedInt(buf.getShort());
    }

    // 32-bit signed int
    public int readFixedI32() {
        return buf.getInt();
    }

    // 32-bit unsigned int
    public long readFixedU32() {
        return Integer.toUnsignedLong(buf.getInt());
    }

    // 64-bit signed int
    public long readFixedI64() {
        return buf.getLong();
    }

    // 32-bit float
    public float readF32() {
        return buf.getFloat();
    }

    // 64-bit float
    public double readF64() {
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
