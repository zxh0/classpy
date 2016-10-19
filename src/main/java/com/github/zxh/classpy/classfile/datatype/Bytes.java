package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;

/**
 * Unparsed bytes.
 */
public class Bytes extends ClassComponent {

    private UInt count;

    public Bytes(UInt count) {
        this.count = count;
    }

    @Override
    protected void readContent(ClassReader reader) {
        reader.skipBytes(count.getValue());
    }

}
