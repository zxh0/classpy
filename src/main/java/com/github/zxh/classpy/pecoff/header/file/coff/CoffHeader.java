package com.github.zxh.classpy.pecoff.header.file.coff;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt32;
import com.github.zxh.classpy.pecoff.datatype.UInt32Hex;

/**
 *
 * @author zxh
 */
public class CoffHeader extends PeComponent {

    private UInt16Hex machine;
    private UInt16 numberOfSections;
    private UInt32 timeDateStamp;
    private UInt32Hex pointerToSymbolTable;
    private UInt32 numberOfSymbols;
    private UInt16 sizeOfOptionalHeader;
    private Characteristics characteristics;

    public UInt16 getNumberOfSections() {
        return numberOfSections;
    }
    
    @Override
    protected void readContent(PeReader reader) {
        machine = reader.readUInt16Hex();
        machine.setDesc(machine.getDesc() + "(" + MachineType.getMachineType(machine.getValue()) + ")");
        numberOfSections = reader.readUInt16();
        timeDateStamp = reader.readUInt32();
        pointerToSymbolTable = reader.readUInt32Hex();
        numberOfSymbols = reader.readUInt32();
        sizeOfOptionalHeader = reader.readUInt16();
        characteristics = new Characteristics();
        characteristics.read(reader);
    }
    
}
