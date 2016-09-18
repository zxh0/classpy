package com.github.zxh.classpy.classfile.bytecode

class Sipush(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1("opcode")
        s2("operand")
    }

}
