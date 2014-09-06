package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.pecoff.header.CoffHeader;
import com.github.zxh.classpy.pecoff.header.MsDosStub;
import com.github.zxh.classpy.pecoff.header.OptionalHeader;
import com.github.zxh.classpy.pecoff.header.Signature;

/**
 * The parse result of PE/COFF file.
 * http://msdn.microsoft.com/en-us/windows/hardware/gg463119.aspx
 * 
 * @author zxh
 */
public class PeFile extends PeComponent {

    private MsDosStub dosStub;
    private Signature signature;
    private CoffHeader coffHeader;
    private OptionalHeader optionalHeader;
    
    @Override
    protected void readContent(PeReader reader) {
        dosStub = new MsDosStub();
        dosStub.read(reader);
        signature = new Signature();
        signature.read(reader);
        coffHeader = new CoffHeader();
        coffHeader.read(reader);
        optionalHeader = new OptionalHeader();
        optionalHeader.read(reader);
        // todo
    }
    
}
