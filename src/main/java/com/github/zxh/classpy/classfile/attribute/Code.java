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
        int endPosition = reader.getPosition() + codeLength;
        while (reader.getPosition() < endPosition) {
            byte b = reader.getByteBuffer().get(reader.getPosition());
            Opcode opcode = Opcode.valueOf(Byte.toUnsignedInt(b));
            Instruction instruction = Instruction.create(opcode);
            instruction.read(reader);
            instructions.add(instruction);
        }
    }

    @Override
    public List<? extends ClassComponent> getSubComponents() {
        return instructions;
    }
    
}
