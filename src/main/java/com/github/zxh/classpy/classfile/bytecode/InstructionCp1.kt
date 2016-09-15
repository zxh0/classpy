package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.constant.ConstantPool

/**
 * The instruction whose operand is U1CpIndex.
 */
class InstructionCp1(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1  ("opcode");
        u1cp("operand");
    }

    override fun afterRead(cp: ConstantPool) {
        val operand = super.get("operand")
        desc = "${opcode.name} ${operand.desc}"
    }

}
