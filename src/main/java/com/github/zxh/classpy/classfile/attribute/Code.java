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
        
        int position;
        while ((position = reader.getPosition()) < endPosition) {
            int pc = position - startPosition;
            byte b = reader.getByteBuffer().get(position);
            Opcode opcode = Opcode.valueOf(Byte.toUnsignedInt(b));
            Instruction instruction = Instruction.create(opcode, pc);
            instruction.read(reader);
            instructions.add(instruction);
        }
        
        int maxPc = instructions.get(instructions.size() - 1).getPc();
        int pcWidth = String.valueOf(maxPc).length();
        String fmtStr = "%0" + pcWidth + "d";
        instructions.forEach(instruction -> {
            instruction.setName(String.format(fmtStr, instruction.getPc()));
        });
    }

    @Override
    public List<? extends ClassComponent> getSubComponents() {
        return instructions;
    }
    
}
