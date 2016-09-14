package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.reader.ClassReader;

public class Iinc extends Instruction {

    public Iinc(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        int index = reader.readUnsignedByte();
        int _const = reader.readByte();
        setDesc(getDesc() + " " + index + ", " + _const);
    }
    
}
