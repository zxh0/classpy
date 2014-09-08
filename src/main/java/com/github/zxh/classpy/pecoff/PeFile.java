package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.pecoff.header.file.FileHeaders;

/**
 * The parse result of PE/COFF file.
 * http://msdn.microsoft.com/en-us/windows/hardware/gg463119.aspx
 * 
 * @author zxh
 */
public class PeFile extends PeComponent {

    private FileHeaders fileHeaders;
    
    @Override
    protected void readContent(PeReader reader) {
        fileHeaders = new FileHeaders();
        fileHeaders.read(reader);
        // todo
    }
    
}
