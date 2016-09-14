package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.reader.ClassReader
import com.github.zxh.classpy.helper.StringHelper

open class UInt(
        readInt: (reader: ClassReader) -> Int,
        describeInt: (i: Int, cp: ConstantPool) -> String
    ) : ClassComponent() {

    private val _readInt = readInt
    private val _describeInt = describeInt
    var value: Int = 0

    override  fun readContent(reader: ClassReader) {
        value = _readInt(reader)
    }

    override fun afterRead(cp: ConstantPool) {
        desc = _describeInt(value, cp)
    }
    
}

fun int2String(i: Int, cp: ConstantPool): String = Integer.toString(i)
fun int2Hex(i: Int, cp: ConstantPool): String = StringHelper.toHexString(i)
fun int2CpIndex(i: Int, cp: ConstantPool): String {
    return if (i > 0) "#$i->${cp.getConstantDesc(i)}" else "#$i"
}

class U1 :       UInt(ClassReader::readUnsignedByte,  ::int2String)
class U1CpIndex: UInt(ClassReader::readUnsignedByte,  ::int2CpIndex)
class U2 :       UInt(ClassReader::readUnsignedShort, ::int2String)
class U2CpIndex: UInt(ClassReader::readUnsignedShort, ::int2CpIndex)
class U4 :       UInt(ClassReader::readInt,           ::int2String) // TODO
class U4Hex :    UInt(ClassReader::readInt,           ::int2Hex)
