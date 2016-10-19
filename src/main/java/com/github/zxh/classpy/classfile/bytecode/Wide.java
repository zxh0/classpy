package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.jvm.Opcode;

public class Wide extends Instruction {

    public Wide(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        int wideOpcode = reader.readUnsignedByte();
        if (wideOpcode == Opcode.iinc.opcode) {
            reader.skipBytes(4);
        } else {
            reader.skipBytes(2);
        }
    }
    
}
