package com.github.zxh.classpy.pecoff.header.section;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16;
import com.github.zxh.classpy.pecoff.datatype.UInt32;

/**
 *
 * @author zxh
 */
public class SectionHeader extends PeComponent {

    private SectionName name;
    private UInt32 virtualSize;
    private UInt32 virtualAddress;
    private UInt32 sizeOfRawData;
    private UInt32 pointerToRawData;
    private UInt32 pointerToRelocations;
    private UInt32 pointerToLinenumbers;
    private UInt16 numberOfRelocations;
    private UInt16 numberOfLinenumbers;
    private UInt32 characteristics; // todo
    
    @Override
    protected void readContent(PeReader reader) {
        name = new SectionName();
        name.read(reader);
        setDesc(name.getDesc());
        virtualSize = reader.readUInt32();
        virtualAddress = reader.readUInt32();
        sizeOfRawData = reader.readUInt32();
        pointerToRawData = reader.readUInt32();
        pointerToRelocations = reader.readUInt32();
        pointerToLinenumbers = reader.readUInt32();
        numberOfRelocations = reader.readUInt16();
        numberOfLinenumbers = reader.readUInt16();
        characteristics = reader.readUInt32();
    }
    
}
