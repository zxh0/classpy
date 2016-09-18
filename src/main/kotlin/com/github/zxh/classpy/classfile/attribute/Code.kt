package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.bytecode.InstructionFactory;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U4;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import com.github.zxh.classpy.classfile.bytecode.Opcode;

class Code(val codeLength: U4) : ClassComponent() {
    
    override fun readContent(reader: ClassReader) {
        val startPosition = reader.position;
        val endPosition = startPosition + codeLength.value;
        
        while (reader.position < endPosition) {
            val pc = reader.position - startPosition;
            val b = reader.getByte(reader.position);
            val opcode = Opcode.XXX.valueOf(java.lang.Byte.toUnsignedInt(b));
            val instruction = InstructionFactory.create(opcode, pc);
            instruction.read(reader);
            add(instruction);
        }
    }

    override fun afterRead(cp: ConstantPool) {
        val instructions = super.subComponents!!;

        val maxPc = (instructions[instructions.size - 1] as Instruction).pc;
        val pcWidth = maxPc.toString().length;
        val fmtStr = "%0" + pcWidth + "d";
        for (c in instructions) {
            val instruction = c as Instruction;
            instruction.name = String.format(fmtStr, instruction.pc);
        }
    }

}
