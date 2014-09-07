package com.github.zxh.classpy.pecoff.header.optional;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt32;
import com.github.zxh.classpy.pecoff.datatype.UInt32Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt8;

/**
 *
 * @author zxh
 */

public class StandardFields extends PeComponent {

    private final boolean isPE32Plus;
    private UInt8 majorLinkerVersion;
    private UInt8 minorLinkerVersion;
    private UInt32 sizeOfCode;
    private UInt32 sizeOfInitializedData;
    private UInt32 sizeOfUninitializedData;
    private UInt32Hex addressOfEntryPoint;
    private UInt32Hex baseOfCode;
    private UInt32Hex baseOfData; // absent in PE32+

    public StandardFields(boolean isPE32Plus) {
        this.isPE32Plus = isPE32Plus;
    }

    @Override
    protected void readContent(PeReader reader) {
        majorLinkerVersion = reader.readUInt8();
        minorLinkerVersion = reader.readUInt8();
        sizeOfCode = reader.readUInt32();
        sizeOfInitializedData = reader.readUInt32();
        sizeOfUninitializedData = reader.readUInt32();
        addressOfEntryPoint = reader.readUInt32Hex();
        baseOfCode = reader.readUInt32Hex();
        if (!isPE32Plus) {
            baseOfData = reader.readUInt32Hex();
        }
    }

}
