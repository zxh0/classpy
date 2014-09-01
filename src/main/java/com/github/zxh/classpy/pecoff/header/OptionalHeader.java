package com.github.zxh.classpy.pecoff.header;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;

/**
 *
 * @author zxh
 */
public class OptionalHeader extends PeComponent {

    // optional header magic number
    private static final int PE32 = 0x10b;
    private static final int PE32_PLUS = 0x20b;
    
    private UInt16Hex magicNumber;
    
    @Override
    protected void readContent(PeReader reader) {
        magicNumber = reader.readUInt16Hex();
        if (magicNumber.getValue() != PE32 || magicNumber.getValue() != PE32_PLUS) {
            throw new FileParseException("Invalid optional header magic number!");
        }
    }
    
    public static class StandardFields extends PeComponent {

        @Override
        protected void readContent(PeReader reader) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
