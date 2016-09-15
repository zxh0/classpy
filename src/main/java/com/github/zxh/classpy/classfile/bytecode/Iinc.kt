package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.reader.ClassReader

class Iinc(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    override fun readOperands(reader: ClassReader) {
        val index = reader.readUnsignedByte()
        val const = reader.readByte()
        desc = "${opcode.name} $index, $const"
    }
    
}
