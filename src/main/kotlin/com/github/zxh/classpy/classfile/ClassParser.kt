package com.github.zxh.classpy.classfile

import com.github.zxh.classpy.classfile.constant.ConstantPool
import com.github.zxh.classpy.classfile.reader.ClassReader

object ClassParser {

    @JvmStatic
    fun parse(bytes: ByteArray): ClassFile {
        val cf = ClassFile()
        cf.read(ClassReader(bytes))
        afterRead(cf, cf.getConstantPool())
        return cf
    }

    fun afterRead(cc: ClassComponent, cp: ConstantPool) {
        cc.subComponents?.forEach { c -> afterRead(c, cp) }
        cc.afterRead(cp)
    }

}
