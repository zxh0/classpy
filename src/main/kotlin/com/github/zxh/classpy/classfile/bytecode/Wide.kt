package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.reader.ClassReader

class Wide(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {
    
    override fun readOperands(reader: ClassReader) {
        val wideOpcode = reader.readUnsignedByte()
        if (wideOpcode == Opcode.iinc.opcode) {
            reader.skipBytes(4)
        } else {
            reader.skipBytes(2)
        }
    }
    
}
