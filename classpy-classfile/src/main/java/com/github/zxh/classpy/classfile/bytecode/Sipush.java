package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.jvm.Opcode;

public class Sipush extends Instruction {

    public Sipush(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassFileReader reader) {
        short operand = reader.readShort();
        setDesc(getDesc() + " " + operand);
    }
    
}
