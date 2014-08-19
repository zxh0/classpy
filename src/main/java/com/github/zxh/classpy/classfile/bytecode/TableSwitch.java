package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.ArrayList;
import java.util.List;

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
public class TableSwitch extends Instruction {

    private final List<JumpOffset> jumpOffsets = new ArrayList<>();
    
    public TableSwitch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        // skip padding
        for (int i = 1; (pc + i) %4 != 0; i++) {
            reader.getByteBuffer().get();
        }
        
        JumpOffset defaultOffset = new JumpOffset();
        defaultOffset.read(reader);
        defaultOffset.setName("default");
        defaultOffset.setDesc(String.valueOf(pc + defaultOffset.offset));
        
        int low = reader.getByteBuffer().getInt();
        int high = reader.getByteBuffer().getInt();
        
        // high - low + 1 signed 32-bit offsets
        for (int i = low; i <= high; i++) {
            JumpOffset offset = new JumpOffset();
            offset.read(reader);
            offset.setName(String.valueOf(i));
            offset.setDesc(String.valueOf(pc + offset.offset));
            jumpOffsets.add(offset);
        }
        
        jumpOffsets.add(defaultOffset);
    }

    @Override
    public List<JumpOffset> getSubComponents() {
        return jumpOffsets;
    }
    
    
    public static class JumpOffset extends ClassComponent {

        private int offset;
        
        @Override
        protected void readContent(ClassReader reader) {
            offset = reader.getByteBuffer().getInt();
        }
        
    }
    
}
