package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.jvm.Opcode;

public class InstructionFactory {

    /**
     * Create instruction by opcode.
     * @param opcode
     * @param pc
     * @return
     */
    public static Instruction create(Opcode opcode, int pc) {
        switch (opcode) {
            case ldc_w:
            case ldc2_w:
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
            case _goto:
            case ifnull:
            case ifnonnull: return new Branch(opcode, pc);
            case bipush: return new Bipush(opcode, pc);
            case sipush: return new Sipush(opcode, pc);
            case ldc: return new InstructionCp1(opcode, pc);
            case iinc: return new Iinc(opcode, pc);
            case tableswitch: return new TableSwitch(opcode, pc);
            case lookupswitch: return new LookupSwitch(opcode, pc);
            case invokeinterface: return new InvokeInterface(opcode, pc);
            case invokedynamic: return new InvokeDynamic(opcode, pc);
            case newarray: return new NewArray(opcode, pc);
            case multianewarray: return new Multianewarray(opcode, pc);
            case wide: return new Wide(opcode, pc);
            // todo
            default: return new Instruction(opcode, pc);
        }
    }

}
