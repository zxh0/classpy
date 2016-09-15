package com.github.zxh.classpy.classfile.bytecode

class Bipush(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1("opcode")
        s1("operand")
    }

}
