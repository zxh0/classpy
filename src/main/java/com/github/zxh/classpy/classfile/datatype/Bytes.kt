package com.github.zxh.classpy.classfile.datatype

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.reader.ClassReader

/**
 * Unparsed bytes.
 */
class Bytes(val count: IntType) : ClassComponent() {

    override fun readContent(reader: ClassReader) {
        reader.skipBytes(count.value);
    }

}
