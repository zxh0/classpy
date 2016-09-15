package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.reader.ClassReader

class Sipush(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    override fun readOperands(reader: ClassReader) {
        val operand = reader.readShort();
        desc = "${opcode.name} $operand"
    }
    
}
