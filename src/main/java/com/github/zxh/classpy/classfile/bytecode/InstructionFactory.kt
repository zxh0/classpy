package com.github.zxh.classpy.classfile.bytecode

object InstructionFactory {

    /**
     * Create instruction by opcode.
     * @param opcode
     * @param pc
     * @return
     */
    fun create(opcode: Opcode, pc: Int): Instruction {
        return when (opcode) {
            Opcode.ldc_w,
            Opcode.ldc2_w,
            Opcode.getstatic,
            Opcode.putstatic,
            Opcode.getfield,
            Opcode.putfield,
            Opcode.invokevirtual,
            Opcode.invokespecial,
            Opcode.invokestatic,
            Opcode._new,
            Opcode.anewarray,
            Opcode.checkcast,
            Opcode._instanceof -> InstructionCp2(opcode, pc);
            Opcode.iload,
            Opcode.lload,
            Opcode.fload,
            Opcode.dload,
            Opcode.aload,
            Opcode.istore,
            Opcode.lstore,
            Opcode.fstore,
            Opcode.dstore,
            Opcode.astore -> InstructionU1(opcode, pc);
            Opcode.ifeq,
            Opcode.ifne,
            Opcode.iflt,
            Opcode.ifge,
            Opcode.ifgt,
            Opcode.ifle,
            Opcode.if_icmpeq,
            Opcode.if_icmpne,
            Opcode.if_icmplt,
            Opcode.if_icmpge,
            Opcode.if_icmpgt,
            Opcode.if_icmple,
            Opcode._goto,
            Opcode.ifnull,
            Opcode.ifnonnull -> Branch(opcode, pc);
            Opcode.bipush -> Bipush(opcode, pc);
            Opcode.sipush -> Sipush(opcode, pc);
            Opcode.ldc -> InstructionCp1(opcode, pc);
            Opcode.iinc -> Iinc(opcode, pc);
            Opcode.tableswitch -> TableSwitch(opcode, pc);
            Opcode.lookupswitch -> LookupSwitch(opcode, pc);
            Opcode.invokeinterface -> InvokeInterface(opcode, pc);
            Opcode.invokedynamic -> InvokeDynamic(opcode, pc);
            Opcode.newarray -> NewArray(opcode, pc);
            Opcode.multianewarray -> Multianewarray(opcode, pc);
            Opcode.wide -> Wide(opcode, pc);
            // todo
            else -> Instruction(opcode, pc);
        }
    }

}
