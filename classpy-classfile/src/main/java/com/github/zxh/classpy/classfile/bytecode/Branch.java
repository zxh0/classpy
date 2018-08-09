package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.jvm.Opcode;

public class Branch extends Instruction {

    public Branch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassFileReader reader) {
        short offset = reader.readShort();
        int jmpTo = pc + offset;
        setDesc(getDesc() + " " + jmpTo);
    }
    
}
