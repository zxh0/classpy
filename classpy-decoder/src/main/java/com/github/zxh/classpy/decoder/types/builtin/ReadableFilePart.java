package com.github.zxh.classpy.decoder.types.builtin;

import com.github.zxh.classpy.common.AbstractFilePart;
import com.github.zxh.classpy.common.BytesReader;

public abstract class ReadableFilePart extends AbstractFilePart {

    public void read(BytesReader reader) {
        int offset = reader.getPosition();
        readContent(reader);
        int length = reader.getPosition() - offset;

        super.setOffset(offset);
        super.setLength(length);
    }

    protected abstract void readContent(BytesReader reader);

}
