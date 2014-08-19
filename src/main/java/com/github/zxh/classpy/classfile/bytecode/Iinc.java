package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;

/**
 *
 * @author zxh
 */
public class Iinc extends Instruction {

    public Iinc(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        int index = reader.readU1().getValue();
        int _const = reader.getByteBuffer().get();
        setDesc(getDesc() + " " + index + ", " + _const);
    }
    
}
