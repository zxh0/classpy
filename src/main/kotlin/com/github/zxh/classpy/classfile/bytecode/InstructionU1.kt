package com.github.zxh.classpy.classfile.bytecode

/**
 * The instruction whose operand is U1.
 */
class InstructionU1(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1("opcode")
        u1("operand")
    }

}
