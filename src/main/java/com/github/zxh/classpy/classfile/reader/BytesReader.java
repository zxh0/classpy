package com.github.zxh.classpy.classfile.reader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BytesReader {
    
    protected final ByteBuffer buf;

    public BytesReader(byte[] bytes, ByteOrder order, boolean readOnly) {
        ByteBuffer _buf = ByteBuffer.wrap(bytes);
        this.buf = readOnly
                ? _buf.asReadOnlyBuffer().order(order)
                : _buf.order(order);
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

}
