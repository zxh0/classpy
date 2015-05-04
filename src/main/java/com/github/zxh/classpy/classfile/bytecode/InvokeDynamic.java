package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/**
 *
 * @author zxh
 */
public class InvokeDynamic extends Instruction {

    public InvokeDynamic(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        U2CpIndex cpIdx = reader.readU2CpIndex();
        reader.skipBytes(2);
        setDesc(getDesc() + " " + cpIdx.getDesc());
    }
    
}
