package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/**
 * The instruction whose operand is U2CpIndex.
 *
 * @author zxh
 */
public class InstructionCp2 extends Instruction {

    public InstructionCp2(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassReader reader) {
        U2CpIndex operand = reader.readU2CpIndex();
        setDesc(getDesc() + " " + operand.getDesc());
    }
    
}
