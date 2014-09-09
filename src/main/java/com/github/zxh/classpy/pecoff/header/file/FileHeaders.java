package com.github.zxh.classpy.pecoff.header.file;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.header.file.coff.CoffHeader;
import com.github.zxh.classpy.pecoff.header.file.optional.OptionalHeader;

/**
 *
 * @author zxh
 */
public class FileHeaders extends PeComponent {

    private MsDosStub msDosStub;
    private Signature signature;
    private CoffHeader coffHeader;
    private OptionalHeader optionalHeader;

    public CoffHeader getCoffHeader() {
        return coffHeader;
    }
    
    @Override
    protected void readContent(PeReader reader) {
        msDosStub = new MsDosStub();
        msDosStub.read(reader);
        signature = new Signature();
        signature.read(reader);
        coffHeader = new CoffHeader();
        coffHeader.read(reader);
        optionalHeader = new OptionalHeader();
        optionalHeader.read(reader);
    }
    
}
