package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.Hex;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;

/**
 *
 * @author zxh
 */
public class HeaderItem extends DexComponent {

    private FileMagic magic;
    private UInt checksum;
    private Hex signature;
    private UInt fileSize;
    private UInt headerSize;
    private Hex endianTag;
    private UInt linkSize;
    private UInt linkOff;
    private UInt mapOff;
    private UInt stringIdsSize;
    private UInt stringIdsOff;
    private UInt typeIdsSize;
    private UInt typeIdsOff;
    private UInt protoIdsSize;
    private UInt protoIdsOff;
    private UInt fieldIdsSize;
    private UInt fieldIdsOff;
    private UInt methodIdsSize;
    private UInt methodIdsOff;
    private UInt classDefsSize;
    private UInt classDefsOff;
    private UInt dataSize;
    private UInt dataOff;
    
    @Override
    protected void readContent(DexReader reader) {
        magic = new FileMagic();
        magic.read(reader);
        checksum = reader.readUInt();
        signature = reader.readHex(20);
        fileSize = reader.readUInt();
        headerSize = reader.readUInt();
        endianTag = reader.readHex(4);
        linkSize = reader.readUInt();
        linkOff = reader.readUInt();
        mapOff = reader.readUInt();
        stringIdsSize = reader.readUInt();
        stringIdsOff = reader.readUInt();
        typeIdsSize = reader.readUInt();
        typeIdsOff = reader.readUInt();
        protoIdsSize = reader.readUInt();
        protoIdsOff = reader.readUInt();
        fieldIdsSize = reader.readUInt();
        fieldIdsOff = reader.readUInt();
        methodIdsSize = reader.readUInt();
        methodIdsOff = reader.readUInt();
        classDefsSize = reader.readUInt();
        classDefsOff = reader.readUInt();
        dataSize = reader.readUInt();
        dataOff = reader.readUInt();
    }
    
}
