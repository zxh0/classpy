package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/**
 *
 * @author zxh
 */
public class Multianewarray extends Instruction {

    public Multianewarray(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        U2CpIndex cpIdx = reader.readU2CpIndex();
        int dimensions = reader.readU1().getValue();
        setDesc(getDesc() + " " + cpIdx.getDesc() + ", " + dimensions);
    }
    
}
