package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;

/**
 *
 * @author zxh
 */
public class Instruction extends ClassComponent {

    protected final Opcode opcode;
    protected final int pc;

    public Instruction(Opcode opcode, int pc) {
        this.opcode = opcode;
        this.pc = pc;
        setDesc(opcode.name());
    }

    public int getPc() {
        return pc;
    }
    
    @Override
    protected final void readContent(ClassReader reader) {
        reader.readU1(); // opcode
        readOperands(reader);
    }
    
    protected void readOperands(ClassReader reader) {
        for (int i = 0; i < opcode.operandCount; i++) {
            reader.readU1();
        }
    }
    
    
    public static Instruction create(Opcode opcode, int pc) {
        switch (opcode) {
            case ldc: return new InstructionCp1(opcode, pc);
            case ldc_w:
            case ldc2_w: return new InstructionCp2(opcode, pc);
            case iload: 
            case lload:
            case fload: 
            case dload:
            case aload:
            case istore:
            case lstore:
            case fstore: 
            case dstore: 
            case astore: return new InstructionU1(opcode, pc);
            case ifeq:
            case ifne:
            case iflt:
            case ifge:
            case ifgt:
            case ifle:
            case if_icmpeq:
            case if_icmpne:
            case if_icmplt:
            case if_icmpge:
            case if_icmpgt:
            case if_icmple:
            case _goto: return new Branch(opcode, pc);
            case tableswitch: return new TableSwitch(opcode, pc);
            case lookupswitch: return new LookupSwitch(opcode, pc);
            case getstatic:
            case putstatic:
            case getfield:
            case putfield:
            case invokevirtual:
            case invokespecial:
            case invokestatic:
            case _new:
            case anewarray:
            case checkcast:
            case _instanceof: return new InstructionCp2(opcode, pc);
            case wide: return new Wide(opcode, pc);
            case ifnull:
            case ifnonnull: return new Branch(opcode, pc);
            // todo
            default: return new Instruction(opcode, pc);
        }
    }

}
