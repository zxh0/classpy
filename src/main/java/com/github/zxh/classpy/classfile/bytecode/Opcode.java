package com.github.zxh.classpy.classfile.bytecode;

/**
 *
 * @author zxh
 */
public enum Opcode {
    
    // Constants
    NOP        (0x00, 0),
    aconst_null(0x01, 0),
    iconst_m1  (0x02, 0),
    iconst_0   (0x03, 0),
    iconst_1   (0x04, 0),
    iconst_2   (0x05, 0),
    iconst_3   (0x06, 0),
    iconst_4   (0x07, 0),
    iconst_5   (0x08, 0),
    lconst_0   (0x09, 0),
    lconst_1   (0x0a, 0),
    fconst_0   (0x0b, 0),
    fconst_1   (0x0c, 0),
    fconst_2   (0x0d, 0),
    dconst_0   (0x0e, 0),
    dconst_1   (0x0f, 0),
    bipush     (0x10, 0),
    sipush     (0x11, 0),
    ldc        (0x12, 0),
    ldc_w      (0x13, 0),
    ldc2_w     (0x14, 0),
;    
    public final int opcode;
    public final int operandCount;

    private Opcode(int opcode, int operandCount) {
        this.opcode = opcode;
        this.operandCount = operandCount;
    }
    
}
