package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.constant.ConstantPool
import com.github.zxh.classpy.classfile.reader.ClassReader

/**
 * Base class for all instructions.
 */
open class Instruction(val opcode: Opcode, val pc: Int) : ClassComponent() {

    override fun readContent(reader: ClassReader) {
        if (super.subComponents == null || super.subComponents!!.isEmpty()) {
            super.readContent(reader);
        } else {
            reader.readUnsignedByte(); // opcode
            readOperands(reader);
        }
    }
    
    protected open fun readOperands(reader: ClassReader) {
        if (opcode.operandCount > 0) {
            reader.skipBytes(opcode.operandCount);
        }
    }

    override fun afterRead(cp: ConstantPool) {
        //val subComponents = super.subComponents;
        if (subComponents?.size == 2) {
            val operand = subComponents!![1]
            desc = opcode.name + operand.desc;
        } else {
            // todo
            desc = opcode.name
        }
    }

}
