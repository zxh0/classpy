package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt32;
import com.github.zxh.classpy.pecoff.datatype.UInt32Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt64;
import com.github.zxh.classpy.pecoff.datatype.UInt64Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt8;
import com.github.zxh.classpy.pecoff.datatype.UInt8Hex;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public class PeReader extends BytesReader {

    public PeReader(byte[] bytes) {
        super(bytes, ByteOrder.LITTLE_ENDIAN, true);
    }
    
    public UInt8 readUInt8() {
        UInt8 uint = new UInt8();
        uint.read(this);
        return uint;
    }
    
    public UInt8Hex readUInt8Hex() {
        UInt8Hex uint = new UInt8Hex();
        uint.read(this);
        return uint;
    }
    
    public UInt16 readUInt16() {
        UInt16 uint = new UInt16();
        uint.read(this);
        return uint;
    }
    
    public UInt16Hex readUInt16Hex() {
        UInt16Hex uint = new UInt16Hex();
        uint.read(this);
        return uint;
    }
    
    public UInt32 readUInt32() {
        UInt32 uint = new UInt32();
        uint.read(this);
        return uint;
    }
    
    public UInt32Hex readUInt32Hex() {
        UInt32Hex uint = new UInt32Hex();
        uint.read(this);
        return uint;
    }
    
    public UInt64 readUInt64() {
        UInt64 uint = new UInt64();
        uint.read(this);
        return uint;
    }
    
    public UInt64Hex readUInt64Hex() {
        UInt64Hex uint = new UInt64Hex();
        uint.read(this);
        return uint;
    }
    
}
