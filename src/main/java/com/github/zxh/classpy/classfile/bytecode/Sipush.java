package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.reader.ClassReader;

public class Sipush extends Instruction {

    public Sipush(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassReader reader) {
        short operand = reader.readShort();
        setDesc(getDesc() + " " + operand);
    }
    
}
