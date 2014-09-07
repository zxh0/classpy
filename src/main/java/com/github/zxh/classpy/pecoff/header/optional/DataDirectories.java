package com.github.zxh.classpy.pecoff.header.optional;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt32;

/**
 *
 * @author zxh
 */
public class DataDirectories extends PeComponent {

    private DataDirectory exportTable;
    private DataDirectory importTable;
    private DataDirectory resourceTable;
    private DataDirectory exceptionTable;
    private DataDirectory certificateTable;
    private DataDirectory baseRelocationTable;
    private DataDirectory debug;
    private DataDirectory architecture;
    private DataDirectory globalPtr;
    private DataDirectory tlsTable;
    private DataDirectory loadConfigTable;
    private DataDirectory boundImport;
    private DataDirectory iat;
    private DataDirectory delayImportDescriptor;
    private DataDirectory clrRuntimeHeader;
    // Reserved, must be zero
    
    @Override
    protected void readContent(PeReader reader) {
        exportTable = readDataDirectory(reader);
        importTable = readDataDirectory(reader);
        resourceTable = readDataDirectory(reader);
        exceptionTable = readDataDirectory(reader);
        certificateTable = readDataDirectory(reader);
        baseRelocationTable = readDataDirectory(reader);
        debug = readDataDirectory(reader);
        architecture = readDataDirectory(reader);
        globalPtr = readDataDirectory(reader);
        tlsTable = readDataDirectory(reader);
        loadConfigTable = readDataDirectory(reader);
        boundImport = readDataDirectory(reader);
        iat = readDataDirectory(reader);
        delayImportDescriptor = readDataDirectory(reader);
        clrRuntimeHeader = readDataDirectory(reader);
    }
    
    private static DataDirectory readDataDirectory(PeReader reader) {
        DataDirectory dd = new DataDirectory();
        dd.read(reader);
        return dd;
    }

    
    public static class DataDirectory extends PeComponent {

        private UInt32 virtualAddress;
        private UInt32 size;
        
        @Override
        protected void readContent(PeReader reader) {
            virtualAddress = reader.readUInt32();
            size = reader.readUInt32();
        }
        
    }
    
}
