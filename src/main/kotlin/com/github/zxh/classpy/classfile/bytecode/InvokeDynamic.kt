package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.constant.ConstantPool

class InvokeDynamic(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1  ("opcode");
        u2cp("index");
        u2  ("zero");
    }

    override fun afterRead(cp: ConstantPool) {
        val operand = super.get("index")!!
        desc = "${opcode.name} ${operand.desc}"
    }
    
}
