package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.constant.ConstantPool

class InvokeInterface(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1  ("opcode")
        u2cp("index")
        u1  ("count")
        u1  ("zero")
    }

    override fun afterRead(cp: ConstantPool) {
        val index = super.get("index").desc
        val count = super.getInt("count")
        desc = "${opcode.name} $index, $count"
    }
    
}
