package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.jvm.Opcode;

/**
 * The instruction whose operand is U2CpIndex.
 */
public class InstructionCp2 extends Instruction {

    {
        u1  ("opcode");
        u2cp("operand");
    }

    public InstructionCp2(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    protected void afterRead(ConstantPool cp) {
        setDesc(getDesc() + " " + super.get("operand").getDesc());
    }
    
}
