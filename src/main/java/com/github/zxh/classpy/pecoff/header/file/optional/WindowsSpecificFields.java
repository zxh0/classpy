package com.github.zxh.classpy.pecoff.header.file.optional;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt32;

/**
 *
 * @author zxh
 */
public class WindowsSpecificFields extends PeComponent {

    private final boolean isPE32Plus;
    private PeComponent imageBase;
    private UInt32 sectionAlignment;
    private UInt32 fileAlignment;
    private UInt16 majorOperatingSystemVersion;
    private UInt16 minorOperatingSystemVersion;
    private UInt16 majorImageVersion;
    private UInt16 minorImageVersion;
    private UInt16 majorSubsystemVersion;
    private UInt16 minorSubsystemVersion;
    private UInt32 win32VersionValue; // Reserved, must be zero.
    private UInt32 sizeOfImage;
    private UInt32 sizeOfHeaders;
    private UInt32 checkSum;
    private UInt16 subsystem;
    private UInt16Hex dllCharacteristics; // todo
    private PeComponent sizeOfStackReserve;
    private PeComponent sizeOfStackCommit;
    private PeComponent sizeOfHeapReserve;
    private PeComponent sizeOfHeapCommit;
    private UInt32 loaderFlags; // Reserved, must be zero.
    private UInt32 numberOfRvaAndSizes;
    // todo

    public WindowsSpecificFields(boolean isPE32Plus) {
        this.isPE32Plus = isPE32Plus;
    }

    @Override
    protected void readContent(PeReader reader) {
        imageBase = isPE32Plus
                ? reader.readUInt64Hex()
                : reader.readUInt32Hex();
        sectionAlignment = reader.readUInt32();
        describeAlignment(sectionAlignment);
        fileAlignment = reader.readUInt32();
        describeAlignment(fileAlignment);
        majorOperatingSystemVersion = reader.readUInt16();
        minorOperatingSystemVersion = reader.readUInt16();
        majorImageVersion = reader.readUInt16();
        minorImageVersion = reader.readUInt16();
        majorSubsystemVersion = reader.readUInt16();
        minorSubsystemVersion = reader.readUInt16();
        win32VersionValue = reader.readUInt32();
        if (win32VersionValue.getValue() != 0) {
            throw new FileParseException("Win32VersionValue is not 0!");
        }
        sizeOfImage = reader.readUInt32();
        sizeOfHeaders = reader.readUInt32();
        checkSum = reader.readUInt32();
        subsystem = reader.readUInt16();
        subsystem.setDesc(subsystem.getDesc() + "(" + WindowsSubsystem.getSubsystem(subsystem.getValue()) + ")");
        dllCharacteristics = reader.readUInt16Hex();
        sizeOfStackReserve = isPE32Plus ? reader.readUInt64() : reader.readUInt32();
        sizeOfStackCommit = isPE32Plus ? reader.readUInt64() : reader.readUInt32();
        sizeOfHeapReserve = isPE32Plus ? reader.readUInt64() : reader.readUInt32();
        sizeOfHeapCommit = isPE32Plus ? reader.readUInt64() : reader.readUInt32();
        loaderFlags = reader.readUInt32();
        if (loaderFlags.getValue() != 0) {
            throw new FileParseException("LoaderFlags is not 0!");
        }
        numberOfRvaAndSizes = reader.readUInt32();
    }

    private void describeAlignment(UInt32 alignment) {
        if (alignment.getValue() > 1024) {
            alignment.setDesc((alignment.getValue() / 1024) + "K");
        }
    }

}