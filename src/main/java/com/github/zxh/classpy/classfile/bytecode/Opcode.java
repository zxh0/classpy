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
    // Loads
    iload      (0x15, 0),
    lload      (0x16, 0),
    fload      (0x17, 0),
    dload      (0x18, 0),
    aload      (0x19, 0),
    iload_0    (0x1a, 0),
    iload_1    (0x1b, 0),
    iload_2    (0x1c, 0),
    iload_3    (0x1d, 0),
    lload_0    (0x1e, 0),
    lload_1    (0x1f, 0),
    lload_2    (0x20, 0),
    lload_3    (0x21, 0),
    fload_0    (0x22, 0),
    fload_1    (0x23, 0),
    fload_2    (0x24, 0),
    fload_3    (0x25, 0),
    dload_0    (0x26, 0),
    dload_1    (0x27, 0),
    dload_2    (0x28, 0),
    dload_3    (0x29, 0),
    aload_0    (0x2a, 0),
    aload_1    (0x2b, 0),
    aload_2    (0x2c, 0),
    aload_3    (0x2d, 0),
    iaload     (0x2e, 0),
    laload     (0x2f, 0),
    faload     (0x30, 0),
    daload     (0x31, 0),
    aaload     (0x32, 0),
    baload     (0x33, 0),
    caload     (0x34, 0),
    saload     (0x35, 0),
    ;
    
    public final int opcode;
    public final int operandCount;

    private Opcode(int opcode, int operandCount) {
        this.opcode = opcode;
        this.operandCount = operandCount;
    }
    
}
