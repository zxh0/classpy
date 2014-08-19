package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;

/**
 *
 * @author zxh
 */
public class Wide extends Instruction {

    public Wide(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        int wideOpcode = reader.readU1().getValue();
        if (wideOpcode == Opcode.iinc.opcode) {
            reader.skipBytes(4);
        } else {
            reader.skipBytes(2);
        }
    }
    
}
