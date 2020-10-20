package com.github.zxh.classpy.ethereum;

import com.github.zxh.classpy.common.AbstractFilePart;
import com.github.zxh.classpy.common.FilePart;

public class EvmBinComponent extends AbstractFilePart {

    public final void read(EvmBinReader reader) {
        int offset = reader.getPosition();
        readContent(reader);
        int length = reader.getPosition() - offset;
        super.setOffset(offset);
        super.setLength(length);
    }

    protected void readContent(EvmBinReader reader) {
        for (FilePart part : getParts()) {
            ((EvmBinComponent) part).read(reader);
        }
    }

}
