package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for TableSwitch and LookupSwitch.
 */
public abstract class Switch extends Instruction {

    protected final List<JumpOffset> jumpOffsets = new ArrayList<>();
    
    public Switch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    protected void skipPadding(ClassReader reader) {
        for (int i = 1; (pc + i) %4 != 0; i++) {
            reader.getByteBuffer().get();
        }
    }
    
    protected JumpOffset readJumpOffset(ClassReader reader, String name) {
        JumpOffset offset = new JumpOffset();
        offset.read(reader);
        offset.setName(name);
        offset.setDesc(String.valueOf(pc + offset.offset));
        return offset;
    }

    @Override
    public final List<JumpOffset> getSubComponents() {
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
