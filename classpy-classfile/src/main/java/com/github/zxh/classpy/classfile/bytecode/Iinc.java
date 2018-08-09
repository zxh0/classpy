package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.jvm.Opcode;

public class Iinc extends Instruction {

    public Iinc(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassFileReader reader) {
        int index = reader.readUnsignedByte();
        int _const = reader.readByte();
        setDesc(getDesc() + " " + index + ", " + _const);
    }
    
}
