package com.github.zxh.classpy.pecoff.header;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import com.github.zxh.classpy.pecoff.datatype.UInt32;
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
    
    @Override
    protected void readContent(PeReader reader) {
        magic = reader.readUInt16Hex();
        if (magic.getValue() != PE32 && magic.getValue() != PE32_PLUS) {
            throw new FileParseException("Invalid optional header magic number!");
        }
        
        standardFields = new StandardFields(magic);
        standardFields.read(reader);
    }
    
    public static class StandardFields extends PeComponent {

        private final UInt16Hex magic;
        private UInt8 majorLinkerVersion;
        private UInt8 minorLinkerVersion;
        private UInt32 sizeOfCode;
        private UInt32 sizeOfUninitializedData;
        private UInt32 addressOfEntryPoint;
        private UInt32 baseOfCode;
        private UInt32 baseOfData; // absent in PE32+

        public StandardFields(UInt16Hex magic) {
            this.magic = magic;
        }
        
        @Override
        protected void readContent(PeReader reader) {
            majorLinkerVersion = reader.readUInt8();
            minorLinkerVersion = reader.readUInt8();
            sizeOfCode = reader.readUInt32();
            sizeOfUninitializedData = reader.readUInt32();
            addressOfEntryPoint = reader.readUInt32();
            baseOfCode = reader.readUInt32();
            if (magic.getValue() != PE32_PLUS) {
                baseOfData = reader.readUInt32();
            }
        }
        
    }
    
    public static class WindowsSpecificFields extends PeComponent {

        @Override
        protected void readContent(PeReader reader) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public static class DataDirectories extends PeComponent {

        @Override
        protected void readContent(PeReader reader) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
