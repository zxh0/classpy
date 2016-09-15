package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.reader.ClassReader

class Multianewarray(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1  ("opcode");
        u2cp("index");
        u1  ("dimensions");
    }

    override fun readOperands(reader: ClassReader) {
        val index = super.get("index").desc
        val dimensions = super.getInt("dimensions")
        desc = "${opcode.name} $index, $dimensions"
    }
    
}
