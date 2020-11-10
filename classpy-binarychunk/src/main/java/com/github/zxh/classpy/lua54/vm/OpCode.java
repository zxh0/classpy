package com.github.zxh.classpy.lua54.vm;

/**
 * LuaVM OpCode.
 * @see /lua5.4.1/src/lopcodes.h
 */
public enum OpCode {

    /*            MM OT IT T  A  mode       */
    OP_MOVE      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_LOADI     (0, 0, 0, 0, 1, OpMode.iAsBx),
    OP_LOADF     (0, 0, 0, 0, 1, OpMode.iAsBx),
    OP_LOADK     (0, 0, 0, 0, 1, OpMode.iABx),
    OP_LOADKX    (0, 0, 0, 0, 1, OpMode.iABx),
    OP_LOADFALSE (0, 0, 0, 0, 1, OpMode.iABC),
    OP_LFALSESKIP(0, 0, 0, 0, 1, OpMode.iABC),
    OP_LOADTRUE  (0, 0, 0, 0, 1, OpMode.iABC),
    OP_LOADNIL   (0, 0, 0, 0, 1, OpMode.iABC),
    OP_GETUPVAL  (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SETUPVAL  (0, 0, 0, 0, 0, OpMode.iABC),
    OP_GETTABUP  (0, 0, 0, 0, 1, OpMode.iABC),
    OP_GETTABLE  (0, 0, 0, 0, 1, OpMode.iABC),
    OP_GETI      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_GETFIELD  (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SETTABUP  (0, 0, 0, 0, 0, OpMode.iABC),
    OP_SETTABLE  (0, 0, 0, 0, 0, OpMode.iABC),
    OP_SETI      (0, 0, 0, 0, 0, OpMode.iABC),
    OP_SETFIELD  (0, 0, 0, 0, 0, OpMode.iABC),
    OP_NEWTABLE  (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SELF      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_ADDI      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_ADDK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SUBK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_MULK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_MODK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_POWK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_DIVK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_IDIVK     (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BANDK     (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BORK      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BXORK     (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SHRI      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SHLI      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_ADD       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SUB       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_MUL       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_MOD       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_POW       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_DIV       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_IDIV      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BAND      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BOR       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BXOR      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SHL       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_SHR       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_MMBIN     (1, 0, 0, 0, 0, OpMode.iABC),
    OP_MMBINI    (1, 0, 0, 0, 0, OpMode.iABC),
    OP_MMBINK    (1, 0, 0, 0, 0, OpMode.iABC),
    OP_UNM       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_BNOT      (0, 0, 0, 0, 1, OpMode.iABC),
    OP_NOT       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_LEN       (0, 0, 0, 0, 1, OpMode.iABC),
    OP_CONCAT    (0, 0, 0, 0, 1, OpMode.iABC),
    OP_CLOSE     (0, 0, 0, 0, 0, OpMode.iABC),
    OP_TBC       (0, 0, 0, 0, 0, OpMode.iABC),
    OP_JMP       (0, 0, 0, 0, 0, OpMode.isJ),
    OP_EQ        (0, 0, 0, 1, 0, OpMode.iABC),
    OP_LT        (0, 0, 0, 1, 0, OpMode.iABC),
    OP_LE        (0, 0, 0, 1, 0, OpMode.iABC),
    OP_EQK       (0, 0, 0, 1, 0, OpMode.iABC),
    OP_EQI       (0, 0, 0, 1, 0, OpMode.iABC),
    OP_LTI       (0, 0, 0, 1, 0, OpMode.iABC),
    OP_LEI       (0, 0, 0, 1, 0, OpMode.iABC),
    OP_GTI       (0, 0, 0, 1, 0, OpMode.iABC),
    OP_GEI       (0, 0, 0, 1, 0, OpMode.iABC),
    OP_TEST      (0, 0, 0, 1, 0, OpMode.iABC),
    OP_TESTSET   (0, 0, 0, 1, 1, OpMode.iABC),
    OP_CALL      (0, 1, 1, 0, 1, OpMode.iABC),
    OP_TAILCALL  (0, 1, 1, 0, 1, OpMode.iABC),
    OP_RETURN    (0, 0, 1, 0, 0, OpMode.iABC),
    OP_RETURN0   (0, 0, 0, 0, 0, OpMode.iABC),
    OP_RETURN1   (0, 0, 0, 0, 0, OpMode.iABC),
    OP_FORLOOP   (0, 0, 0, 0, 1, OpMode.iABx),
    OP_FORPREP   (0, 0, 0, 0, 1, OpMode.iABx),
    OP_TFORPREP  (0, 0, 0, 0, 0, OpMode.iABx),
    OP_TFORCALL  (0, 0, 0, 0, 0, OpMode.iABC),
    OP_TFORLOOP  (0, 0, 0, 0, 1, OpMode.iABx),
    OP_SETLIST   (0, 0, 1, 0, 0, OpMode.iABC),
    OP_CLOSURE   (0, 0, 0, 0, 1, OpMode.iABx),
    OP_VARARG    (0, 1, 0, 0, 1, OpMode.iABC),
    OP_VARARGPREP(0, 0, 1, 0, 1, OpMode.iABC),
    OP_EXTRAARG  (0, 0, 0, 0, 0, OpMode.iAx),
    ;

    public final byte flagA;  // instruction set register A
    public final byte flagT;  // operator is a test (next instruction must be a jump)
    public final byte flagIT; // instruction uses 'L->top' set by previous instruction (when B == 0)
    public final byte flagOT; // instruction sets 'L->top' for next instruction (when C == 0)
    public final byte flagMM; // instruction is an MM instruction (call a metamethod)
    public final OpMode mode; // instruction format

    OpCode(int mm, int ot, int it, int t, int a, OpMode mode) {
        this.flagA = (byte) a;
        this.flagT = (byte) t;
        this.flagIT = (byte) it;
        this.flagOT = (byte) ot;
        this.flagMM = (byte) mm;
        this.mode = mode;
    }

}
