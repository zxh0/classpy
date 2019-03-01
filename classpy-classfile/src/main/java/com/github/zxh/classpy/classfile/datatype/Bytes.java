package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassFilePart;
import com.github.zxh.classpy.classfile.ClassFileReader;

/**
 * Unparsed bytes.
 */
public class Bytes extends ClassFilePart {

    private UInt count;

    public Bytes(UInt count) {
        this.count = count;
    }

    @Override
    protected void readContent(ClassFileReader reader) {
        reader.skipBytes(count.getValue());
    }

}
