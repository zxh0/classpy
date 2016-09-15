package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.reader.ClassReader

class Bipush(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    override fun readOperands(reader: ClassReader) {
        val operand = reader.readByte()
        desc = "${opcode.name} $operand"
    }
    
}
