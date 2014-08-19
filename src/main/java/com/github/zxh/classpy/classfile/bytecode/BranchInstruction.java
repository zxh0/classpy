package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;

/**
 *
 * @author zxh
 */
public class BranchInstruction extends Instruction {

    public BranchInstruction(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        short offset = reader.getByteBuffer().getShort();
        offset += pc;
        setDesc(getDesc() + " " + offset);
    }
    
}
