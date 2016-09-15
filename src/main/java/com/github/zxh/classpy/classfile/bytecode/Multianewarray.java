package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.reader.ClassReader;

public class Multianewarray extends Instruction {

    {
        u1  ("opcode");
        u2cp("index");
        u1  ("dimensions");
    }

    public Multianewarray(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        setDesc(getDesc() + " "
                + super.get("index").getDesc() + ", "
                + super.getInt("dimensions"));
    }
    
}
