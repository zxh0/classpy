package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2CpIndex;

/**
 *
 * @author zxh
 */
public class InvokeInterface extends Instruction {

    public InvokeInterface(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        U2CpIndex cpIdx = reader.readU2CpIndex();
        int count = reader.readU1().getValue();
        setDesc(getDesc() + " " + cpIdx.getDesc() + " , " + count);
    }
    
}
