package com.github.zxh.classpy.classfile.bytecode

/**
 * The instruction whose operand is U2CpIndex.
 */
class InstructionCp2(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1  ("opcode")
        u2cp("operand")
    }

}
