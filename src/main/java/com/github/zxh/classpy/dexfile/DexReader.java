package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.data.DataList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
    
    public void setPosition(UInt newPosition) {
        buf.position(newPosition.getValue());
    }
    
    public void setPosition(int newPosition) {
        buf.position(newPosition);
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
    
    public byte[] readMUTF8Bytes() {
        int nextZeroPos;
        for (nextZeroPos = buf.position(); ; nextZeroPos++) {
            if (buf.get(nextZeroPos) == 0) {
                break;
            }
        }
        
        return readBytes(nextZeroPos - buf.position());
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
    public UShort readUShort() {
        UShort ushort = new UShort();
        ushort.read(this);
        return ushort;
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
    
    // Unsigned Little-Endian Base 128.
    public Uleb128 readUleb128() {
        Uleb128 uleb = new Uleb128();
        uleb.read(this);
        return uleb;
    }
    
    public Utf8String readUtf8String() {
        Utf8String str = new Utf8String();
        str.read(this);
        return str;
    }
    
    public Hex readHex(int n) {
        Hex hex = new Hex(n);
        hex.readContent(this);
        return hex;
    }
    
    public <E extends DexComponent> DexList<E> readDexList(UInt size, Supplier<E> factory) {
        return readDexList(size.getValue(), factory);
    }
    
    public <E extends DexComponent> DexList<E> readDexList(Uleb128 size, Supplier<E> factory) {
        return readDexList(size.getValue(), factory);
    }
    
    public <E extends DexComponent> DexList<E> readDexList(int size, Supplier<E> factory) {
        DexList<E> list = new DexList<>(size, factory);
        list.read(this);
        return list;
    }
    
    public <E extends DexComponent> DataList<E> readDataList(Supplier<E> factory, Stream<UInt> offStream) {
        DataList<E> list = new DataList<>(offStream, factory);
        list.read(this);
        return list;
    }
    
}
