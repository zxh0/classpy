package com.github.zxh.classpy.pecoff.header;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.header.coff.CoffHeader;
import com.github.zxh.classpy.pecoff.header.optional.OptionalHeader;

/**
 *
 * @author zxh
 */
public class FileHeaders extends PeComponent{

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
    }
    
}
