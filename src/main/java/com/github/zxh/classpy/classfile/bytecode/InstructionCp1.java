package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1CpIndex;

/**
 *
 * @author zxh
 */
public class InstructionCp1 extends Instruction {

    public InstructionCp1(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassReader reader) {
        U1CpIndex operand = reader.readU1CpIndex();
        setDesc(getDesc() + " " + operand.getDesc());
    }
    
}
