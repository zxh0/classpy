package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.jvm.Opcode;

public class Bipush extends Instruction {

    public Bipush(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassReader reader) {
        byte operand = reader.readByte();
        setDesc(getDesc() + " " + operand);
    }
    
}
