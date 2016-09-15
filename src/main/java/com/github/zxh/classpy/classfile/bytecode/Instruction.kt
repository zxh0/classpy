package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.constant.ConstantPool
import com.github.zxh.classpy.classfile.reader.ClassReader

/**
 * Base class for all instructions.
 */
class Instruction(opcode: Opcode, pc: Int) : ClassComponent() {

    protected val opcode = opcode
    protected val pc = pc

    override fun readContent(reader: ClassReader) {
        if (!super.getSubComponents().isEmpty()) {
            super.readContent(reader);
        } else {
            reader.readUnsignedByte(); // opcode
            readOperands(reader);
        }
    }
    
    protected fun readOperands(reader: ClassReader) {
        if (opcode.operandCount > 0) {
            reader.skipBytes(opcode.operandCount);
        }
    }

    override fun afterRead(cp: ConstantPool) {
        val subComponents = super.getSubComponents();
        if (subComponents.size == 2) {
            val operand = subComponents[1]
            desc = opcode.name + operand.desc;
        } else {
            // todo
            desc = opcode.name
        }
    }

}
