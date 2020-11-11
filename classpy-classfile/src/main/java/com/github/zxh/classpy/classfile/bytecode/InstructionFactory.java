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
        // TODO
        return switch (opcode) {
            case ldc_w, ldc2_w,
                    getstatic, putstatic, getfield, putfield,
                    invokevirtual, invokespecial, invokestatic,
                    _new, anewarray, checkcast, _instanceof -> new InstructionCp2(opcode, pc);
            case iload, lload, fload, dload, aload,
                    istore, lstore, fstore, dstore, astore -> new InstructionU1(opcode, pc);
            case ifeq, ifne, iflt, ifge, ifgt, ifle,
                    if_icmpeq, if_icmpne, if_icmplt, if_icmpge, if_icmpgt, if_icmple,
                    _goto, ifnull, ifnonnull -> new Branch(opcode, pc);
            case bipush -> new Bipush(opcode, pc);
            case sipush -> new Sipush(opcode, pc);
            case ldc -> new InstructionCp1(opcode, pc);
            case iinc -> new Iinc(opcode, pc);
            case tableswitch -> new TableSwitch(opcode, pc);
            case lookupswitch -> new LookupSwitch(opcode, pc);
            case invokeinterface -> new InvokeInterface(opcode, pc);
            case invokedynamic -> new InvokeDynamic(opcode, pc);
            case newarray -> new NewArray(opcode, pc);
            case multianewarray -> new Multianewarray(opcode, pc);
            case wide -> new Wide(opcode, pc);
            default -> new Instruction(opcode, pc);
        };
    }

}
