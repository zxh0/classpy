package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.jvm.Opcode;

public class InvokeInterface extends Instruction {

    {
        u1  ("opcode");
        u2cp("index");
        u1  ("count");
        u1  ("zero");
    }

    public InvokeInterface(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void postRead(ConstantPool cp) {
        setDesc(getDesc() + " "
                + super.get("index").getDesc() + ", "
                + super.getUInt("count"));
    }
    
}
