package com.github.zxh.classpy.pecoff.header.file;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt8Hex;

/**
 *
 * @author zxh
 */
public class MsDosStub extends PeComponent {

    private UInt8Hex peSignatureOffset;
    
    @Override
    protected void readContent(PeReader reader) {
        reader.skipBytes(0x3c);
        peSignatureOffset = reader.readUInt8Hex();
        reader.skipBytes(peSignatureOffset.getValue() - 0x3c - 1);
    }
    
}
