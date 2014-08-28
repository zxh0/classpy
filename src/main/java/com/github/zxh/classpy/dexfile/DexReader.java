package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.dexfile.index.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.index.UIntTypeIdIndex;
import com.github.zxh.classpy.dexfile.index.UShortTypeIdIndex;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import com.github.zxh.classpy.dexfile.list.OffsetsKnownList;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;
import java.nio.ByteOrder;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 *
 * @author zxh
 */
public class DexReader extends BytesReader {

    public DexReader(byte[] bytes) {
        super(bytes, ByteOrder.LITTLE_ENDIAN, false);
    }
    
    public void setPosition(UInt newPosition) {
        buf.position(newPosition.getValue());
    }
    
    public void setPosition(int newPosition) {
        buf.position(newPosition);
    }
    
    public byte[] readMutf8Bytes() {
        int nextZeroPos;
        for (nextZeroPos = buf.position(); ; nextZeroPos++) {
            if (buf.get(nextZeroPos) == 0) {
                break;
            }
        }
        
        int numOfNonZeroBytes = nextZeroPos - buf.position();
        byte[] bytes = readBytes(numOfNonZeroBytes);
        buf.get(); // skip zero
        
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
    public UShort readUShort() {
        UShort ushort = new UShort();
        ushort.read(this);
        return ushort;
    }
    
    public UShortTypeIdIndex readUShortTypeIdIndex() {
        UShortTypeIdIndex ushort = new UShortTypeIdIndex();
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
    
    public UIntStringIdIndex readUIntStringIdIndex() {
        UIntStringIdIndex uint = new UIntStringIdIndex();
        uint.read(this);
        return uint;
    }
    
    public UIntTypeIdIndex readUIntTypeIdIndex() {
        UIntTypeIdIndex uint = new UIntTypeIdIndex();
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
    
    public Mutf8 readUtf8String() {
        Mutf8 str = new Mutf8();
        str.read(this);
        return str;
    }
    
    public Hex readHex(int n) {
        Hex hex = new Hex(n);
        hex.readContent(this);
        return hex;
    }
    
    public <E extends DexComponent> SizeKnownList<E> readSizeKnownList(UInt size, Supplier<E> factory) {
        return readSizeKnownList(size.getValue(), factory);
    }
    
    public <E extends DexComponent> SizeKnownList<E> readSizeKnownList(Uleb128 size, Supplier<E> factory) {
        return readSizeKnownList(size.getValue(), factory);
    }
    
    public <E extends DexComponent> SizeKnownList<E> readSizeKnownList(int size, Supplier<E> factory) {
        SizeKnownList<E> list = new SizeKnownList<>(size, factory);
        list.read(this);
        return list;
    }
    
    public <E extends DexComponent> OffsetsKnownList<E> readOffsetsKnownList(Supplier<E> factory, IntStream offStream) {
        OffsetsKnownList<E> list = new OffsetsKnownList<>(offStream, factory);
        list.read(this);
        return list;
    }
    
    public <E extends DexComponent> SizeHeaderList<E> readSizeHeaderList(Supplier<E> factory) {
        SizeHeaderList<E> list = new SizeHeaderList<>(factory);
        list.read(this);
        return list;
    }
    
}
