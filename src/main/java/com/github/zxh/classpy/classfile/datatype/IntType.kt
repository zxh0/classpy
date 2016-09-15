package com.github.zxh.classpy.classfile.datatype

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.constant.ConstantPool
import com.github.zxh.classpy.classfile.reader.ClassReader

open class IntType(
        readInt: (reader: ClassReader) -> Int,
        describeInt: (i: Int, cp: ConstantPool) -> String
    ) : ClassComponent() {

    private val readInt = readInt
    private val describeInt = describeInt
    var value: Int = 0

    override fun readContent(reader: ClassReader) {
        value = readInt(reader)
    }

    override fun afterRead(cp: ConstantPool) {
        desc = describeInt(value, cp)
    }
    
}

private fun int2String(i: Int, cp: ConstantPool): String {
    return Integer.toString(i)
}
private fun int2Hex(i: Int, cp: ConstantPool): String {
    return "0x" + Integer.toHexString(i).toUpperCase()
}
private fun int2CpIndex(i: Int, cp: ConstantPool): String {
    return if (i > 0) "#$i->${cp.getConstantDesc(i)}" else "#$i"
}

class U1:        IntType(ClassReader::readUnsignedByte,  ::int2String )
class U1Hex:     IntType(ClassReader::readUnsignedByte,  ::int2Hex    )
class U1CpIndex: IntType(ClassReader::readUnsignedByte,  ::int2CpIndex)
class U2:        IntType(ClassReader::readUnsignedShort, ::int2String )
class U2CpIndex: IntType(ClassReader::readUnsignedShort, ::int2CpIndex)
class U4:        IntType(ClassReader::readInt,           ::int2String )
class U4Hex:     IntType(ClassReader::readInt,           ::int2Hex    )
class S1:        IntType(ClassReader::readByte,          ::int2String )