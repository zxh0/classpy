package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UShort;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.datatype.Mutf8;
import com.github.zxh.classpy.dexfile.datatype.Hex;
import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.dexfile.datatype.ByteArray;
import com.github.zxh.classpy.dexfile.datatype.SInt;
import com.github.zxh.classpy.dexfile.datatype.Sleb128;
import com.github.zxh.classpy.dexfile.datatype.UByte;
import com.github.zxh.classpy.dexfile.datatype.UIntFieldIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.datatype.UIntMethodIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UIntTypeIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UShortProtoIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UShortTypeIdIndex;
import com.github.zxh.classpy.dexfile.datatype.Uleb128Hex;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import com.github.zxh.classpy.dexfile.list.OffsetsKnownList;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;
import java.nio.ByteOrder;
import java.util.function.Supplier;

/**
 *
 * @author zxh
 */
public class DexReader extends BytesReader {

    public DexReader(byte[] bytes) {
        super(bytes, ByteOrder.LITTLE_ENDIAN, false);
    }
    
    public void setPosition(IntValue newPosition) {
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
    
    public UByte readUByte() {
        UByte ubyte = new UByte();
        ubyte.read(this);
        return ubyte;
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
    
    public UShortProtoIdIndex readUShortProtoIdIndex() {
        UShortProtoIdIndex ushort = new UShortProtoIdIndex();
        ushort.read(this);
        return ushort;
    }
    
    // 32-bit signed int, little-endian
    public SInt readSInt() {
        SInt sint = new SInt();
        sint.read(this);
        return sint;
    }
    
    // 32-bit unsigned int, little-endian
    public UInt readUInt() {
        UInt uint = new UInt();
        uint.read(this);
        return uint;
    }
    
    public UIntHex readUIntHex() {
        UIntHex uint = new UIntHex();
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
    
    public UIntFieldIdIndex readUIntFieldIdIndex() {
        UIntFieldIdIndex uint = new UIntFieldIdIndex();
        uint.read(this);
        return uint;
    }
    
    public UIntMethodIdIndex readUIntMethodIdIndex() {
        UIntMethodIdIndex uint = new UIntMethodIdIndex();
        uint.read(this);
        return uint;
    }
    
    // Unsigned Little-Endian Base 128.
    public Uleb128 readUleb128() {
        Uleb128 uleb = new Uleb128();
        uleb.read(this);
        return uleb;
    }
    
    public Uleb128Hex readUleb128Hex() {
        Uleb128Hex uleb = new Uleb128Hex();
        uleb.read(this);
        return uleb;
    }
    
    // Signed Little-Endian Base 128.
    public Sleb128 readSleb128() {
        Sleb128 uleb = new Sleb128();
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
        hex.read(this);
        return hex;
    }
    
    public ByteArray readByteArray(int n) {
        ByteArray array = new ByteArray(n);
        array.read(this);
        return array;
    }
    
    public <E extends DexComponent> SizeKnownList<E> readSizeKnownList(IntValue size, Supplier<E> factory) {
        return readSizeKnownList(size.getValue(), factory);
    }
    
    public <E extends DexComponent> SizeKnownList<E> readSizeKnownList(int size, Supplier<E> factory) {
        SizeKnownList<E> list = new SizeKnownList<>(size, factory);
        list.read(this);
        return list;
    }
    
    public <E extends DexComponent> OffsetsKnownList<E> readOffsetsKnownList(int[] offsets, Supplier<E> factory) {
        OffsetsKnownList<E> list = new OffsetsKnownList<>(offsets, factory);
        list.read(this);
        return list;
    }
    
    public <E extends DexComponent> SizeHeaderList<E> readSizeHeaderList(Supplier<E> factory) {
        SizeHeaderList<E> list = new SizeHeaderList<>(factory);
        list.read(this);
        return list;
    }
    
}
