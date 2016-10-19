package com.github.zxh.classpy.luacout.lvm;

/**
 * LuaVM OpCode.
 * @see /lua/src/lopcodes.h
 */
public enum OpCode {

    // name     args     description
    OP_MOVE    ("AB",   "R(A) := R(B)"),
    OP_LOADK   ("ABx",  "R(A) := Kst(Bx)"),
    OP_LOADKX  ("A",    "R(A) := Kst(extra arg)"),
    OP_LOADBOOL("ABC",  "R(A) := (bool)B; if (C) pc++"),
    OP_LOADNIL ("AB",   "R(A), R(A+1), ..., R(A+B) := nil"),
    OP_GETUPVAL("AB",   "R(A) := UpValue[B]"),
    OP_GETTABUP("ABC",  "R(A) := UpValue[B][RK(C)]"),
    OP_GETTABLE("ABC",  "R(A) := R(B)[RK(C)]"),
    OP_SETTABUP("ABC",  "UpValue[A][RK(B)] := RK(C)"),
    OP_SETUPVAL("AB",   "UpValue[B] := R(A)"),
    OP_SETTABLE("ABC",  "R(A)[RK(B)] := RK(C)"),
    OP_NEWTABLE("ABC",  "R(A) := {} (size = B,C)"),
    OP_SELF    ("ABC",  "R(A+1) := R(B); R(A) := R(B)[RK(C)]"),
    OP_ADD     ("ABC",  "R(A) := RK(B) + RK(C)"),
    OP_SUB     ("ABC",  "R(A) := RK(B) - RK(C)"),
    OP_MUL     ("ABC",  "R(A) := RK(B) * RK(C)"),
    OP_MOD     ("ABC",  "R(A) := RK(B) % RK(C)"),
    OP_POW     ("ABC",  "R(A) := RK(B) ^ RK(C)"),
    OP_DIV     ("ABC",  "R(A) := RK(B) / RK(C)"),
    OP_IDIV    ("ABC",  "R(A) := RK(B) // RK(C)"),
    OP_BAND    ("ABC",  "R(A) := RK(B) & RK(C)"),
    OP_BOR     ("ABC",  "R(A) := RK(B) | RK(C)"),
    OP_BXOR    ("ABC",  "R(A) := RK(B) ~ RK(C)"),
    OP_SHL     ("ABC",  "R(A) := RK(B) << RK(C)"),
    OP_SHR     ("ABC",  "R(A) := RK(B) >> RK(C)"),
    OP_UNM     ("AB",   "R(A) := -R(B)"),
    OP_BNOT    ("AB",   "R(A) := ~R(B)"),
    OP_NOT     ("AB",   "R(A) := not R(B)"),
    OP_LEN     ("AB",   "R(A) := length of R(B)"),
    OP_CONCAT  ("ABC",  "R(A) := R(B).. ... ..R(C)"),
    OP_JMP     ("AsBx", "pc+=sBx; if (A) close all upvalues >= R(A - 1)"),
    OP_EQ      ("ABC",  "if ((RK(B) == RK(C)) ~= A) then pc++"),
    OP_LT      ("ABC",  "if ((RK(B) <  RK(C)) ~= A) then pc++"),
    OP_LE      ("ABC",  "if ((RK(B) <= RK(C)) ~= A) then pc++"),
    OP_TEST    ("AC",   "if not (R(A) <=> C) then pc++"),
    OP_TESTSET ("ABC",  "if (R(B) <=> C) then R(A) := R(B) else pc++ "),
    OP_CALL    ("ABC",  "R(A), ... ,R(A+C-2) := R(A)(R(A+1), ... ,R(A+B-1))"),
    OP_TAILCALL("ABC",  "return R(A)(R(A+1), ... ,R(A+B-1))"),
    OP_RETURN  ("AB",   "return R(A), ... ,R(A+B-2)"),
    OP_FORLOOP ("AsBx", "R(A)+=R(A+2); if R(A) <?= R(A+1) then { pc+=sBx; R(A+3)=R(A) }"),
    OP_FORPREP ("AsBx", "R(A)-=R(A+2); pc+=sBx"),
    OP_TFORCALL("AC",   "R(A+3), ... ,R(A+2+C) := R(A)(R(A+1), R(A+2));"),
    OP_TFORLOOP("AsBx", "if R(A+1) ~= nil then { R(A)=R(A+1); pc += sBx }"),
    OP_SETLIST ("ABC",  "R(A)[(C-1)*FPF+i] := R(A+i), 1 <= i <= B"),
    OP_CLOSURE ("ABx",  "R(A) := closure(KPROTO[Bx])"),
    OP_VARARG  ("AB",   "R(A), R(A+1), ..., R(A+B-2) = vararg"),
    OP_EXTRAARG("Ax",   "extra (larger) argument for previous opcode"),
    ;

    public final String args;
    public final String description;

    private OpCode(String args, String description) {
        this.args = args;
        this.description = description;
    }

}
