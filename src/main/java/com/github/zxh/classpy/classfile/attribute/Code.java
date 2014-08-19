package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import com.github.zxh.classpy.classfile.bytecode.Opcode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class Code extends ClassComponent {

    private final int codeLength;
    private final List<Instruction> instructions = new ArrayList<>();

    public Code(int codeLength) {
        this.codeLength = codeLength;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        final int startPosition = reader.getPosition();
        final int endPosition = startPosition + codeLength;
        
        List<Integer> codeOffsets = new ArrayList<>();
        
        int position;
        while ((position = reader.getPosition()) < endPosition) {
            codeOffsets.add(position - startPosition);
            
            byte b = reader.getByteBuffer().get(position);
            Opcode opcode = Opcode.valueOf(Byte.toUnsignedInt(b));
            Instruction instruction = Instruction.create(opcode);
            instruction.read(reader);
            instructions.add(instruction);
        }
        
        int maxOffset = codeOffsets.get(codeOffsets.size() - 1);
        int offsetWidth = String.valueOf(maxOffset).length();
        String fmtStr = "%0" + offsetWidth + "d";
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            int codeOffset = codeOffsets.get(i);
            instruction.setName(String.format(fmtStr, codeOffset));
        }
    }

    @Override
    public List<? extends ClassComponent> getSubComponents() {
        return instructions;
    }
    
}
