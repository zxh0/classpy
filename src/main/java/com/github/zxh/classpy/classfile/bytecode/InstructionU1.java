package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;

/**
 *
 * @author zxh
 */
public class InstructionU1 extends Instruction {

    public InstructionU1(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassReader reader) {
        U1 operand = reader.readU1();
        setDesc(getDesc() + " " + operand.getDesc());
    }
    
}
