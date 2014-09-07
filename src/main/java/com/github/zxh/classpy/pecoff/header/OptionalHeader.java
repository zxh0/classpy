package com.github.zxh.classpy.pecoff.header;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt32;
import com.github.zxh.classpy.pecoff.datatype.UInt32Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt8;

/**
 *
 * @author zxh
 */
public class OptionalHeader extends PeComponent {

    // optional header magic number
    private static final int PE32 = 0x10b;
    private static final int PE32_PLUS = 0x20b;
    
    private UInt16Hex magic;
    private StandardFields standardFields;
    private WindowsSpecificFields windowsSpecificFields;
    
    @Override
    protected void readContent(PeReader reader) {
        magic = reader.readUInt16Hex();
        if (magic.getValue() != PE32 && magic.getValue() != PE32_PLUS) {
            throw new FileParseException("Invalid optional header magic number!");
        }
        
        boolean isPE32Plus = (magic.getValue() == PE32_PLUS);
        standardFields = new StandardFields(isPE32Plus);
        standardFields.read(reader);
        windowsSpecificFields = new WindowsSpecificFields(isPE32Plus);
        windowsSpecificFields.read(reader);
    }
    
    
    public static class StandardFields extends PeComponent {

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
    
    public static class WindowsSpecificFields extends PeComponent {

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
            sizeOfImage = reader.readUInt32();
            sizeOfHeaders = reader.readUInt32();
            checkSum = reader.readUInt32();
        }
        
        private void describeAlignment(UInt32 alignment) {
            if (alignment.getValue() > 1024) {
                alignment.setDesc((alignment.getValue() / 1024) + "K");
            }
        }
        
    }
    
    public static class DataDirectories extends PeComponent {

        @Override
        protected void readContent(PeReader reader) {
            // todo
        }
        
    }
    
}
