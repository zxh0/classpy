package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.reader.ClassReader

class Branch(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    override fun readOperands(reader: ClassReader) {
        val offset = reader.readShort() + pc
        desc = "${opcode.name} $offset"
    }
    
}
