package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;

/*
lookupswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
npairs1
npairs2
npairs3
npairs4
match-offset pairs...
 */
public class LookupSwitch extends Instruction {

    public LookupSwitch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
//        skipPadding(reader);
//        
//        JumpOffset defaultOffset = readJumpOffset(reader, "default");
//        int npairs = reader.getByteBuffer().getInt();
//        
//        for (int i = 0; i < npairs; i++) {
//            readJumpOffset(reader, "");
//            
//        }
    }
    
}
