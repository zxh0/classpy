package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassReader;

/*
    tableswitch
    <0-3 byte pad>
    defaultbyte1
    defaultbyte2
    defaultbyte3
    defaultbyte4
    lowbyte1
    lowbyte2
    lowbyte3
    lowbyte4
    highbyte1
    highbyte2
    highbyte3
    highbyte4
    jump offsets...
 */
public class TableSwitch extends Switch {

    public TableSwitch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        // skip padding
        for (int i = 1; (pc + i) %4 != 0; i++) {
            reader.getByteBuffer().get();
        }
        
        JumpOffset defaultOffset = readJumpOffset(reader, "default");
        
        int low = reader.getByteBuffer().getInt();
        int high = reader.getByteBuffer().getInt();
        
        // high - low + 1 signed 32-bit offsets
        for (int i = low; i <= high; i++) {
            JumpOffset offset = readJumpOffset(reader, String.valueOf(i));
            jumpOffsets.add(offset);
        }
        
        jumpOffsets.add(defaultOffset);
    }
    
}
