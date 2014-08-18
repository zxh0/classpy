package com.github.zxh.classpy.classfile.bytecode;

/**
 *
 * @author zxh
 */
public enum Opcode {
    
    // Constants
    nop        (0x00, 0),
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
    // Stores
    istore     (0x36, 0),
    lstore     (0x37, 0),
    fstore     (0x38, 0),
    dstore     (0x39, 0),
    astore     (0x3a, 0),
    istore_0   (0x3b, 0),
    istore_1   (0x3c, 0),
    istore_2   (0x3d, 0),
    istore_3   (0x3e, 0),
    lstore_0   (0x3f, 0),
    lstore_1   (0x40, 0),
    lstore_2   (0x41, 0),
    lstore_3   (0x42, 0),
    fstore_0   (0x43, 0),
    fstore_1   (0x44, 0),
    fstore_2   (0x45, 0),
    fstore_3   (0x46, 0),
    dstore_0   (0x47, 0),
    dstore_1   (0x48, 0),
    dstore_2   (0x49, 0),
    dstore_3   (0x4a, 0),
    astore_0   (0x4b, 0),
    astore_1   (0x4c, 0),
    astore_2   (0x4d, 0),
    astore_3   (0x4e, 0),
    iastore    (0x4f, 0),
    lastore    (0x50, 0),
    fastore    (0x51, 0),
    dastore    (0x52, 0),
    aastore    (0x53, 0),
    bastore    (0x54, 0),
    castore    (0x55, 0),
    sastore    (0x56, 0),
    // Stack
    pop        (0x57, 0),
    pop2       (0x58, 0),
    dup        (0x59, 0),
    dup_x1     (0x5a, 0),
    dup_x2     (0x5b, 0),
    dup2       (0x5c, 0),
    dup2_x1    (0x5d, 0),
    dup2_x2    (0x5e, 0),
    swap       (0x5f, 0),
//     Math


//96 (0x60) iadd
//97 (0x61) ladd
//98 (0x62) fadd
//99 (0x63) dadd
//100 (0x64) isub
//101 (0x65) lsub
//102 (0x66) fsub
//103 (0x67) dsub
//104 (0x68) imul
//105 (0x69) lmul 106 (0x6a) fmul 107 (0x6b) dmul 108 (0x6c) idiv 109 (0x6d) ldiv 110 (0x6e) fdiv 111 (0x6f) ddiv 112 (0x70) irem 113 (0x71) lrem 114 (0x72) frem 115 (0x73) drem 116 (0x74) ineg 117 (0x75) lneg 118 (0x76) fneg 119 (0x77) dneg 120 (0x78) ishl 121 (0x79) lshl 122 (0x7a) ishr 123 (0x7b) lshr 124 (0x7c) iushr 125 (0x7d) lushr 126 (0x7e) iand 127 (0x7f) land 128 (0x80) ior 129 (0x81) lor 130 (0x82) ixor 131 (0x83) lxor 132 (0x84) iinc
//Conversions
//133 (0x85) i2l 134 (0x86) i2f 135 (0x87) i2d 136 (0x88) l2i 137 (0x89) l2f 138 (0x8a) l2d 139 (0x8b) f2i 140 (0x8c) f2l 141 (0x8d) f2d 142 (0x8e) d2i 143 (0x8f) d2l 144 (0x90) d2f 145 (0x91) i2b 146 (0x92) i2c 147 (0x93) i2s
    ;
    
    public final int opcode;
    public final int operandCount;

    private Opcode(int opcode, int operandCount) {
        this.opcode = opcode;
        this.operandCount = operandCount;
    }
    
}
