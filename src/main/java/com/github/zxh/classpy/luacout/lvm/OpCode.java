package com.github.zxh.classpy.luacout.lvm;

/**
 * LuaVM OpCode.
 * @see /lua/src/lopcodes.h
 */
public enum OpCode {

    //           T      A      B                 C                 mode
    OP_MOVE    (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iABC),
    OP_LOADK   (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgN, OpMode.iABx),
    OP_LOADKX  (false, true,  OpArgMask.OpArgN, OpArgMask.OpArgN, OpMode.iABx),
    OP_LOADBOOL(false, true,  OpArgMask.OpArgU, OpArgMask.OpArgU, OpMode.iABC),
    OP_LOADNIL (false, true,  OpArgMask.OpArgU, OpArgMask.OpArgN, OpMode.iABC),
    OP_GETUPVAL(false, true,  OpArgMask.OpArgU, OpArgMask.OpArgN, OpMode.iABC),
    OP_GETTABUP(false, true,  OpArgMask.OpArgU, OpArgMask.OpArgK, OpMode.iABC),
    OP_GETTABLE(false, true,  OpArgMask.OpArgR, OpArgMask.OpArgK, OpMode.iABC),
    OP_SETTABUP(false, false, OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_SETUPVAL(false, false, OpArgMask.OpArgU, OpArgMask.OpArgN, OpMode.iABC),
    OP_SETTABLE(false, false, OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_NEWTABLE(false, true,  OpArgMask.OpArgU, OpArgMask.OpArgU, OpMode.iABC),
    OP_SELF    (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgK, OpMode.iABC),
    OP_ADD     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_SUB     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_MUL     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_MOD     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_POW     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_DIV     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_IDIV    (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_BAND    (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_BOR     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_BXOR    (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_SHL     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_SHR     (false, true,  OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_UNM     (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iABC),
    OP_BNOT    (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iABC),
    OP_NOT     (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iABC),
    OP_LEN     (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iABC),
    OP_CONCAT  (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgR, OpMode.iABC),
    OP_JMP     (false, false, OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iAsBx),
    OP_EQ      (true,  false, OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_LT      (true,  false, OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_LE      (true,  false, OpArgMask.OpArgK, OpArgMask.OpArgK, OpMode.iABC),
    OP_TEST    (true,  false, OpArgMask.OpArgN, OpArgMask.OpArgU, OpMode.iABC),
    OP_TESTSET (true,  true,  OpArgMask.OpArgR, OpArgMask.OpArgU, OpMode.iABC),
    OP_CALL    (false, true,  OpArgMask.OpArgU, OpArgMask.OpArgU, OpMode.iABC),
    OP_TAILCALL(false, true,  OpArgMask.OpArgU, OpArgMask.OpArgU, OpMode.iABC),
    OP_RETURN  (false, false, OpArgMask.OpArgU, OpArgMask.OpArgN, OpMode.iABC),
    OP_FORLOOP (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iAsBx),
    OP_FORPREP (false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iAsBx),
    OP_TFORCALL(false, false, OpArgMask.OpArgN, OpArgMask.OpArgU, OpMode.iABC),
    OP_TFORLOOP(false, true,  OpArgMask.OpArgR, OpArgMask.OpArgN, OpMode.iAsBx),
    OP_SETLIST (false, false, OpArgMask.OpArgU, OpArgMask.OpArgU, OpMode.iABC),
    OP_CLOSURE (false, true,  OpArgMask.OpArgU, OpArgMask.OpArgN, OpMode.iABx),
    OP_VARARG  (false, true,  OpArgMask.OpArgU, OpArgMask.OpArgN, OpMode.iABC),
    OP_EXTRAARG(false, false, OpArgMask.OpArgU, OpArgMask.OpArgU, OpMode.iAx),
    ;

    public final boolean t; // operator is a test (next instruction must be a jump)
    public final boolean a; // instruction set register A
    public final OpArgMask b; // B arg mode
    public final OpArgMask c; // C arg mode
    public final OpMode mode;

    OpCode(boolean t, boolean a, OpArgMask b, OpArgMask c, OpMode mode) {
        this.t = t;
        this.a = a;
        this.b = b;
        this.c = c;
        this.mode = mode;
    }

}
