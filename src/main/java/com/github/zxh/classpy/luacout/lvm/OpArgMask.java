package com.github.zxh.classpy.luacout.lvm;

/*
 * masks for instruction properties. The format is:
 * bits 0-1: op mode
 * bits 2-3: C arg mode
 * bits 4-5: B arg mode
 * bit 6: instruction set register A
 * bit 7: operator is a test (next instruction must be a jump)
 */
public enum OpArgMask {
    OpArgN, /* argument is not used */
    OpArgU, /* argument is used */
    OpArgR, /* argument is a register or a jump offset */
    OpArgK, /* argument is a constant or register/constant */
}
