package com.github.zxh.classpy.pecoff.header.file;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;

/**
 *
 * @author zxh
 */
public class MsDosStub extends PeComponent {

    @Override
    protected void readContent(PeReader reader) {
        reader.skipBytes(0x3c);
        int peSignatureOffset = reader.readUnsignedByte();
        reader.skipBytes(peSignatureOffset - 0x3c - 1);
    }
    
}
