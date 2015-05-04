package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/**
 *
 * @author zxh
 */
public class Branch extends Instruction {

    public Branch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        short offset = reader.readShort();
        offset += pc;
        setDesc(getDesc() + " " + offset);
    }
    
}
