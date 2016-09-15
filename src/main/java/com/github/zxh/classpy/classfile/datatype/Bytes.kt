package com.github.zxh.classpy.classfile.datatype

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.reader.ClassReader

/**
 * Unparsed bytes.
 */
class Bytes(count: IntType) : ClassComponent() {

    private val count = count;

    override fun readContent(reader: ClassReader) {
        reader.skipBytes(count.value);
    }

}
