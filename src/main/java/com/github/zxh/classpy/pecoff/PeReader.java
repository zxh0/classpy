package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public class PeReader extends BytesReader {

    public PeReader(byte[] bytes) {
        super(bytes, ByteOrder.LITTLE_ENDIAN, true);
    }
    
    // todo
    public UInt16Hex readUInt16Hex() {
        UInt16Hex uint = new UInt16Hex();
        uint.read(this);
        return uint;
    }
    
}
