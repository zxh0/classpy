package com.github.zxh.classpy.dexfile.bytecode;

import static com.github.zxh.classpy.dexfile.bytecode.InstructionFormat.*;
/**
 *
 * http://source.android.com/devices/tech/dalvik/dalvik-bytecode.html
 * 
 * @author pc
 */
public class InstructionSet {
    
    static class Instruction {
        
        public final InstructionFormat format;

        private Instruction(InstructionFormat format) {
            this.format = format;
        }
    }
    
    static final Instruction[] iset = new Instruction[256];
    static {
        iset[0x00] = new Instruction(_10x); // nop
        iset[0x01] = new Instruction(_12x); // move vA, vB
        iset[0x02] = new Instruction(_22x); // move/from16 vAA, vBBBB
        iset[0x03] = new Instruction(_32x); // move/16 vAAAA, vBBBB
        iset[0x04] = new Instruction(_12x); // move-wide vA, vB
        iset[0x05] = new Instruction(_22x); // move-wide/from16 vAA, vBBBB
        iset[0x06] = new Instruction(_32x); // move-wide/16 vAAAA, vBBBB
        iset[0x07] = new Instruction(_12x); // move-object vA, vB
        iset[0x08] = new Instruction(_22x); // move-object/from16 vAA, vBBBB
        iset[0x09] = new Instruction(_32x); // move-object/16 vAAAA, vBBBB
        iset[0x0a] = new Instruction(_11x); // move-result vAA
        iset[0x0b] = new Instruction(_11x); // move-result-wide vAA
        iset[0x0c] = new Instruction(_11x); // move-result-object vAA
        iset[0x0d] = new Instruction(_11x); // move-exception vAA
        iset[0x0e] = new Instruction(_10x); // return-void
        iset[0x0f] = new Instruction(_11x); // return vAA
        iset[0x10] = new Instruction(_11x); // return-wide vAA
        iset[0x11] = new Instruction(_11x); // return-object vAA
        iset[0x12] = new Instruction(_11n); // const/4 vA, #+B
        iset[0x13] = new Instruction(_21s); // const/16 vAA, #+BBBB
        iset[0x14] = new Instruction(_31i); // const vAA, #+BBBBBBBB
        iset[0x15] = new Instruction(_21h); // const/high16 vAA, #+BBBB0000
        iset[0x16] = new Instruction(_21s); // const-wide/16 vAA, #+BBBB
        iset[0x17] = new Instruction(_31i); // const-wide/32 vAA, #+BBBBBBBB
        iset[0x18] = new Instruction(_51l); // const-wide vAA, #+BBBBBBBBBBBBBBBB
        iset[0x19] = new Instruction(_21h); // const-wide/high16 vAA, #+BBBB000000000000
        iset[0x1a] = new Instruction(_21c); // const-string vAA, string@BBBB
        iset[0x1b] = new Instruction(_31c); // const-string/jumbo vAA, string@BBBBBBBB
        iset[0x1c] = new Instruction(_21c); // const-class vAA, type@BBBB
        iset[0x1d] = new Instruction(_11x); // monitor-enter vAA
        iset[0x1e] = new Instruction(_11x); // monitor-exit vAA
        iset[0x1f] = new Instruction(_21c); // check-cast vAA, type@BBBB
        iset[0x20] = new Instruction(_22c); // instance-of vA, vB, type@CCCC
        iset[0x21] = new Instruction(_12x); // array-length vA, vB
        iset[0x22] = new Instruction(_21c); // new-instance vAA, type@BBBB
        iset[0x23] = new Instruction(_22c); // new-array vA, vB, type@CCCC
        iset[0x24] = new Instruction(_35c); // filled-new-array {vC, vD, vE, vF, vG}, type@BBBB
        iset[0x25] = new Instruction(_3rc); // filled-new-array/range {vCCCC .. vNNNN}, type@BBBB
        iset[0x26] = new Instruction(_31t); // fill-array-data vAA, +BBBBBBBB
        iset[0x27] = new Instruction(_11x); // throw vAA
        iset[0x28] = new Instruction(_10t); // goto +AA
        iset[0x29] = new Instruction(_20t); // goto/16 +AAAA
        iset[0x2a] = new Instruction(_30t); //goto/32 +AAAAAAAA
        iset[0x2b] = new Instruction(_31t); // packed-switch vAA, +BBBBBBBB
        iset[0x2c] = new Instruction(_31t); // sparse-switch vAA, +BBBBBBBB
//2d..31 23x 	cmpkind vAA, vBB, vCC
//2d: cmpl-float (lt bias)
//2e: cmpg-float (gt bias)
//2f: cmpl-double (lt bias)
//30: cmpg-double (gt bias)
//31: cmp-long 	A: destination register (8 bits)
//B: first source register or pair
//C: second source register or pair 	Perform the indicated floating point or long comparison, setting a to 0 if b == c, 1 if b > c, or -1 if b < c. The "bias" listed for the floating point operations indicates how NaN comparisons are treated: "gt bias" instructions return 1 for NaN comparisons, and "lt bias" instructions return -1.
//
//For example, to check to see if floating point x < y it is advisable to use cmpg-float; a result of -1 indicates that the test was true, and the other values indicate it was false either due to a valid comparison or because one of the values was NaN.
//32..37 22t 	if-test vA, vB, +CCCC
//32: if-eq
//33: if-ne
//34: if-lt
//35: if-ge
//36: if-gt
//37: if-le
//	A: first register to test (4 bits)
//B: second register to test (4 bits)
//C: signed branch offset (16 bits) 	Branch to the given destination if the given two registers' values compare as specified.
//
//Note: The branch offset must not be 0. (A spin loop may be legally constructed either by branching around a backward goto or by including a nop as a target before the branch.)
//38..3d 21t 	if-testz vAA, +BBBB
//38: if-eqz
//39: if-nez
//3a: if-ltz
//3b: if-gez
//3c: if-gtz
//3d: if-lez
//	A: register to test (8 bits)
//B: signed branch offset (16 bits) 	Branch to the given destination if the given register's value compares with 0 as specified.
//
//Note: The branch offset must not be 0. (A spin loop may be legally constructed either by branching around a backward goto or by including a nop as a target before the branch.)
//3e..43 10x 	(unused) 	  	(unused)
//44..51 23x 	arrayop vAA, vBB, vCC
//44: aget
//45: aget-wide
//46: aget-object
//47: aget-boolean
//48: aget-byte
//49: aget-char
//4a: aget-short
//4b: aput
//4c: aput-wide
//4d: aput-object
//4e: aput-boolean
//4f: aput-byte
//50: aput-char
//51: aput-short 	A: value register or pair; may be source or dest (8 bits)
//B: array register (8 bits)
//C: index register (8 bits) 	Perform the identified array operation at the identified index of the given array, loading or storing into the value register.
//52..5f 22c 	iinstanceop vA, vB, field@CCCC
//52: iget
//53: iget-wide
//54: iget-object
//55: iget-boolean
//56: iget-byte
//57: iget-char
//58: iget-short
//59: iput
//5a: iput-wide
//5b: iput-object
//5c: iput-boolean
//5d: iput-byte
//5e: iput-char
//5f: iput-short 	A: value register or pair; may be source or dest (4 bits)
//B: object register (4 bits)
//C: instance field reference index (16 bits) 	Perform the identified object instance field operation with the identified field, loading or storing into the value register.
//
//Note: These opcodes are reasonable candidates for static linking, altering the field argument to be a more direct offset.
//60..6d 21c 	sstaticop vAA, field@BBBB
//60: sget
//61: sget-wide
//62: sget-object
//63: sget-boolean
//64: sget-byte
//65: sget-char
//66: sget-short
//67: sput
//68: sput-wide
//69: sput-object
//6a: sput-boolean
//6b: sput-byte
//6c: sput-char
//6d: sput-short 	A: value register or pair; may be source or dest (8 bits)
//B: static field reference index (16 bits) 	Perform the identified object static field operation with the identified static field, loading or storing into the value register.
//
//Note: These opcodes are reasonable candidates for static linking, altering the field argument to be a more direct offset.
//6e..72 35c 	invoke-kind {vC, vD, vE, vF, vG}, meth@BBBB
//6e: invoke-virtual
//6f: invoke-super
//70: invoke-direct
//71: invoke-static
//72: invoke-interface 	A: argument word count (4 bits)
//B: method reference index (16 bits)
//C..G: argument registers (4 bits each) 	Call the indicated method. The result (if any) may be stored with an appropriate move-result* variant as the immediately subsequent instruction.
//
//invoke-virtual is used to invoke a normal virtual method (a method that is not private, static, or final, and is also not a constructor).
//
//invoke-super is used to invoke the closest superclass's virtual method (as opposed to the one with the same method_id in the calling class). The same method restrictions hold as for invoke-virtual.
//
//invoke-direct is used to invoke a non-static direct method (that is, an instance method that is by its nature non-overridable, namely either a private instance method or a constructor).
//
//invoke-static is used to invoke a static method (which is always considered a direct method).
//
//invoke-interface is used to invoke an interface method, that is, on an object whose concrete class isn't known, using a method_id that refers to an interface.
//
//Note: These opcodes are reasonable candidates for static linking, altering the method argument to be a more direct offset (or pair thereof).
//73 10x 	(unused) 	  	(unused)
//74..78 3rc 	invoke-kind/range {vCCCC .. vNNNN}, meth@BBBB
//74: invoke-virtual/range
//75: invoke-super/range
//76: invoke-direct/range
//77: invoke-static/range
//78: invoke-interface/range 	A: argument word count (8 bits)
//B: method reference index (16 bits)
//C: first argument register (16 bits)
//N = A + C - 1 	Call the indicated method. See first invoke-kind description above for details, caveats, and suggestions.
//79..7a 10x 	(unused) 	  	(unused)
//7b..8f 12x 	unop vA, vB
//7b: neg-int
//7c: not-int
//7d: neg-long
//7e: not-long
//7f: neg-float
//80: neg-double
//81: int-to-long
//82: int-to-float
//83: int-to-double
//84: long-to-int
//85: long-to-float
//86: long-to-double
//87: float-to-int
//88: float-to-long
//89: float-to-double
//8a: double-to-int
//8b: double-to-long
//8c: double-to-float
//8d: int-to-byte
//8e: int-to-char
//8f: int-to-short 	A: destination register or pair (4 bits)
//B: source register or pair (4 bits) 	Perform the identified unary operation on the source register, storing the result in the destination register.
//90..af 23x 	binop vAA, vBB, vCC
//90: add-int
//91: sub-int
//92: mul-int
//93: div-int
//94: rem-int
//95: and-int
//96: or-int
//97: xor-int
//98: shl-int
//99: shr-int
//9a: ushr-int
//9b: add-long
//9c: sub-long
//9d: mul-long
//9e: div-long
//9f: rem-long
//a0: and-long
//a1: or-long
//a2: xor-long
//a3: shl-long
//a4: shr-long
//a5: ushr-long
//a6: add-float
//a7: sub-float
//a8: mul-float
//a9: div-float
//aa: rem-float
//ab: add-double
//ac: sub-double
//ad: mul-double
//ae: div-double
//af: rem-double 	A: destination register or pair (8 bits)
//B: first source register or pair (8 bits)
//C: second source register or pair (8 bits) 	Perform the identified binary operation on the two source registers, storing the result in the first source register.
//b0..cf 12x 	binop/2addr vA, vB
//b0: add-int/2addr
//b1: sub-int/2addr
//b2: mul-int/2addr
//b3: div-int/2addr
//b4: rem-int/2addr
//b5: and-int/2addr
//b6: or-int/2addr
//b7: xor-int/2addr
//b8: shl-int/2addr
//b9: shr-int/2addr
//ba: ushr-int/2addr
//bb: add-long/2addr
//bc: sub-long/2addr
//bd: mul-long/2addr
//be: div-long/2addr
//bf: rem-long/2addr
//c0: and-long/2addr
//c1: or-long/2addr
//c2: xor-long/2addr
//c3: shl-long/2addr
//c4: shr-long/2addr
//c5: ushr-long/2addr
//c6: add-float/2addr
//c7: sub-float/2addr
//c8: mul-float/2addr
//c9: div-float/2addr
//ca: rem-float/2addr
//cb: add-double/2addr
//cc: sub-double/2addr
//cd: mul-double/2addr
//ce: div-double/2addr
//cf: rem-double/2addr 	A: destination and first source register or pair (4 bits)
//B: second source register or pair (4 bits) 	Perform the identified binary operation on the two source registers, storing the result in the first source register.
//d0..d7 22s 	binop/lit16 vA, vB, #+CCCC
//d0: add-int/lit16
//d1: rsub-int (reverse subtract)
//d2: mul-int/lit16
//d3: div-int/lit16
//d4: rem-int/lit16
//d5: and-int/lit16
//d6: or-int/lit16
//d7: xor-int/lit16 	A: destination register (4 bits)
//B: source register (4 bits)
//C: signed int constant (16 bits) 	Perform the indicated binary op on the indicated register (first argument) and literal value (second argument), storing the result in the destination register.
//
//Note: rsub-int does not have a suffix since this version is the main opcode of its family. Also, see below for details on its semantics.
//d8..e2 22b 	binop/lit8 vAA, vBB, #+CC
//d8: add-int/lit8
//d9: rsub-int/lit8
//da: mul-int/lit8
//db: div-int/lit8
//dc: rem-int/lit8
//dd: and-int/lit8
//de: or-int/lit8
//df: xor-int/lit8
//e0: shl-int/lit8
//e1: shr-int/lit8
//e2: ushr-int/lit8 	A: destination register (8 bits)
//B: source register (8 bits)
//C: signed int constant (8 bits) 	Perform the indicated binary op on the indicated register (first argument) and literal value (second argument), storing the result in the destination register.
//
//Note: See below for details on the semantics of rsub-int.
//e3..ff 10x 	(unused) 	  	(unused)
    }

    
}
