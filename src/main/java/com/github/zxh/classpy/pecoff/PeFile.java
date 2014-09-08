package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.pecoff.header.file.FileHeaders;
import com.github.zxh.classpy.pecoff.header.section.SectionTable;

/**
 * The parse result of PE/COFF file.
 * http://msdn.microsoft.com/en-us/windows/hardware/gg463119.aspx
 * 
 * @author zxh
 */
public class PeFile extends PeComponent {

    private FileHeaders fileHeaders;
    private SectionTable sectionTable; // section headers
    
    @Override
    protected void readContent(PeReader reader) {
        fileHeaders = new FileHeaders();
        fileHeaders.read(reader);
        sectionTable = new SectionTable(
                fileHeaders.getCoffHeader().getNumberOfSections().getValue());
        sectionTable.read(reader);
        // todo
    }
    
}
