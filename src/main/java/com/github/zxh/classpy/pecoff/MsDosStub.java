package com.github.zxh.classpy.pecoff;

/**
 *
 * @author zxh
 */
public class MsDosStub extends PeComponent {

    @Override
    protected void readContent(PeReader reader) {
        reader.skipBytes(0x3c);
        int peSignatureOffset = reader.readUByte();
        reader.skipBytes(peSignatureOffset - 0x3c);
    }
    
}
