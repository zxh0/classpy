package com.github.zxh.classpy.classfile.reader;

import com.github.zxh.classpy.classfile.constant.ConstantPool;

/**
 * Convenience class for reading class files.
 */
class ClassReader(bytes: ByteArray) : BytesReader(bytes) {

    var constantPool: ConstantPool? = null;

}
