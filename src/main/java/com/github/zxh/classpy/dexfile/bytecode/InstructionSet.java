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
        iset[0x2a] = new Instruction(_30t); // goto/32 +AAAAAAAA
        iset[0x2b] = new Instruction(_31t); // packed-switch vAA, +BBBBBBBB
        iset[0x2c] = new Instruction(_31t); // sparse-switch vAA, +BBBBBBBB
        //2d..31 23x 	cmpkind vAA, vBB, vCC
        iset[0x2d] = new Instruction(_23x); // cmpl-float (lt bias)
        iset[0x2e] = new Instruction(_23x); // cmpg-float (gt bias)
        iset[0x2f] = new Instruction(_23x); // cmpl-double (lt bias)
        iset[0x30] = new Instruction(_23x); // cmpg-double (gt bias)
        iset[0x31] = new Instruction(_23x); // cmp-long
        //32..37 22t 	if-test vA, vB, +CCCC
        iset[0x32] = new Instruction(_22t); // if-eq
        iset[0x33] = new Instruction(_22t); // if-ne
        iset[0x34] = new Instruction(_22t); // if-lt
        iset[0x35] = new Instruction(_22t); // if-ge
        iset[0x36] = new Instruction(_22t); // if-gt
        iset[0x37] = new Instruction(_22t); // if-le
        //38..3d 21t 	if-testz vAA, +BBBB
        iset[0x38] = new Instruction(_21t); // if-eqz
        iset[0x39] = new Instruction(_21t); // if-nez
        iset[0x3a] = new Instruction(_21t); // if-ltz
        iset[0x3b] = new Instruction(_21t); // if-gez
        iset[0x3c] = new Instruction(_21t); // if-gtz
        iset[0x3d] = new Instruction(_21t); // if-lez
        //3e..43 10x 	(unused) 	  	(unused)
        //44..51 23x 	arrayop vAA, vBB, vCC
        iset[0x44] = new Instruction(_23x); // aget
        iset[0x45] = new Instruction(_23x); // aget-wide
        iset[0x46] = new Instruction(_23x); // aget-object
        iset[0x47] = new Instruction(_23x); // aget-boolean
        iset[0x48] = new Instruction(_23x); // aget-byte
        iset[0x49] = new Instruction(_23x); // aget-char
        iset[0x4a] = new Instruction(_23x); // aget-short
        iset[0x4b] = new Instruction(_23x); // aput
        iset[0x4c] = new Instruction(_23x); // aput-wide
        iset[0x4d] = new Instruction(_23x); // aput-object
        iset[0x4e] = new Instruction(_23x); // aput-boolean
        iset[0x4f] = new Instruction(_23x); // aput-byte
        iset[0x50] = new Instruction(_23x); // aput-char
        iset[0x51] = new Instruction(_23x); // aput-short 	A: value register or pair; may be source or dest (8 bits)
        //52..5f 22c 	iinstanceop vA, vB, field@CCCC
        iset[0x52] = new Instruction(_22c); // iget
        iset[0x53] = new Instruction(_22c); // iget-wide
        iset[0x54] = new Instruction(_22c); // iget-object
        iset[0x55] = new Instruction(_22c); // iget-boolean
        iset[0x56] = new Instruction(_22c); // iget-byte
        iset[0x57] = new Instruction(_22c); // iget-char
        iset[0x58] = new Instruction(_22c); // iget-short
        iset[0x59] = new Instruction(_22c); // iput
        iset[0x5a] = new Instruction(_22c); // iput-wide
        iset[0x5b] = new Instruction(_22c); // iput-object
        iset[0x5c] = new Instruction(_22c); // iput-boolean
        iset[0x5d] = new Instruction(_22c); // iput-byte
        iset[0x5e] = new Instruction(_22c); // iput-char
        iset[0x5f] = new Instruction(_22c); // iput-short 	A: value register or pair; may be source or dest (4 bits)
        //60..6d 21c 	sstaticop vAA, field@BBBB
        iset[0x60] = new Instruction(_21c); // sget
        iset[0x61] = new Instruction(_21c); // sget-wide
        iset[0x62] = new Instruction(_21c); // sget-object
        iset[0x63] = new Instruction(_21c); // sget-boolean
        iset[0x64] = new Instruction(_21c); // sget-byte
        iset[0x65] = new Instruction(_21c); // sget-char
        iset[0x66] = new Instruction(_21c); // sget-short
        iset[0x67] = new Instruction(_21c); // sput
        iset[0x68] = new Instruction(_21c); // sput-wide
        iset[0x69] = new Instruction(_21c); // sput-object
        iset[0x6a] = new Instruction(_21c); // sput-boolean
        iset[0x6b] = new Instruction(_21c); // sput-byte
        iset[0x6c] = new Instruction(_21c); // sput-char
        iset[0x6d] = new Instruction(_21c); // sput-short 	A: value register or pair; may be source or dest (8 bits)
        //6e..72 35c 	invoke-kind {vC, vD, vE, vF, vG}, meth@BBBB
        iset[0x6e] = new Instruction(_35c); // invoke-virtual
        iset[0x6f] = new Instruction(_35c); // invoke-super
        iset[0x70] = new Instruction(_35c); // invoke-direct
        iset[0x71] = new Instruction(_35c); // invoke-static
        iset[0x72] = new Instruction(_35c); // invoke-interface 	A: argument word count (4 bits)
        //73 10x 	(unused) 	  	(unused)
        //74..78 3rc 	invoke-kind/range {vCCCC .. vNNNN}, meth@BBBB
        iset[0x74] = new Instruction(_3rc); // invoke-virtual/range
        iset[0x75] = new Instruction(_3rc); // invoke-super/range
        iset[0x76] = new Instruction(_3rc); // invoke-direct/range
        iset[0x77] = new Instruction(_3rc); // invoke-static/range
        iset[0x78] = new Instruction(_3rc); // invoke-interface/range 	A: argument word count (8 bits)
        //79..7a 10x 	(unused) 	  	(unused)
        //7b..8f 12x 	unop vA, vB
        iset[0x7b] = new Instruction(_12x); // neg-int
        iset[0x7c] = new Instruction(_12x); // not-int
        iset[0x7d] = new Instruction(_12x); // neg-long
        iset[0x7e] = new Instruction(_12x); // not-long
        iset[0x7f] = new Instruction(_12x); // neg-float
        iset[0x80] = new Instruction(_12x); // neg-double
        iset[0x81] = new Instruction(_12x); // int-to-long
        iset[0x82] = new Instruction(_12x); // int-to-float
        iset[0x83] = new Instruction(_12x); // int-to-double
        iset[0x84] = new Instruction(_12x); // long-to-int
        iset[0x85] = new Instruction(_12x); // long-to-float
        iset[0x86] = new Instruction(_12x); // long-to-double
        iset[0x87] = new Instruction(_12x); // float-to-int
        iset[0x88] = new Instruction(_12x); // float-to-long
        iset[0x89] = new Instruction(_12x); // float-to-double
        iset[0x8a] = new Instruction(_12x); // double-to-int
        iset[0x8b] = new Instruction(_12x); // double-to-long
        iset[0x8c] = new Instruction(_12x); // double-to-float
        iset[0x8d] = new Instruction(_12x); // int-to-byte
        iset[0x8e] = new Instruction(_12x); // int-to-char
        iset[0x8f] = new Instruction(_12x); // int-to-short 	A: destination register or pair (4 bits)
        //90..af 23x 	binop vAA, vBB, vCC
        iset[0x90] = new Instruction(_23x); // add-int
        iset[0x91] = new Instruction(_23x); // sub-int
        iset[0x92] = new Instruction(_23x); // mul-int
        iset[0x93] = new Instruction(_23x); // div-int
        iset[0x94] = new Instruction(_23x); // rem-int
        iset[0x95] = new Instruction(_23x); // and-int
        iset[0x96] = new Instruction(_23x); // or-int
        iset[0x97] = new Instruction(_23x); // xor-int
        iset[0x98] = new Instruction(_23x); // shl-int
        iset[0x99] = new Instruction(_23x); // shr-int
        iset[0x9a] = new Instruction(_23x); // ushr-int
        iset[0x9b] = new Instruction(_23x); // add-long
        iset[0x9c] = new Instruction(_23x); // sub-long
        iset[0x9d] = new Instruction(_23x); // mul-long
        iset[0x9e] = new Instruction(_23x); // div-long
        iset[0x9f] = new Instruction(_23x); // rem-long
        iset[0xa0] = new Instruction(_23x); // and-long
        iset[0xa1] = new Instruction(_23x); // or-long
        iset[0xa2] = new Instruction(_23x); // xor-long
        iset[0xa3] = new Instruction(_23x); // shl-long
        iset[0xa4] = new Instruction(_23x); // shr-long
        iset[0xa5] = new Instruction(_23x); // ushr-long
        iset[0xa6] = new Instruction(_23x); // add-float
        iset[0xa7] = new Instruction(_23x); // sub-float
        iset[0xa8] = new Instruction(_23x); // mul-float
        iset[0xa9] = new Instruction(_23x); // div-float
        iset[0xaa] = new Instruction(_23x); // rem-float
        iset[0xab] = new Instruction(_23x); // add-double
        iset[0xac] = new Instruction(_23x); // sub-double
        iset[0xad] = new Instruction(_23x); // mul-double
        iset[0xae] = new Instruction(_23x); // div-double
        iset[0xaf] = new Instruction(_23x); // rem-double 	A: destination register or pair (8 bits)
        //b0..cf 12x 	binop/2addr vA, vB
        iset[0xb0] = new Instruction(_12x); // add-int/2addr
        iset[0xb1] = new Instruction(_12x); // sub-int/2addr
        iset[0xb2] = new Instruction(_12x); // mul-int/2addr
        iset[0xb3] = new Instruction(_12x); // div-int/2addr
        iset[0xb4] = new Instruction(_12x); // rem-int/2addr
        iset[0xb5] = new Instruction(_12x); // and-int/2addr
        iset[0xb6] = new Instruction(_12x); // or-int/2addr
        iset[0xb7] = new Instruction(_12x); // xor-int/2addr
        iset[0xb8] = new Instruction(_12x); // shl-int/2addr
        iset[0xb9] = new Instruction(_12x); // shr-int/2addr
        iset[0xba] = new Instruction(_12x); // ushr-int/2addr
        iset[0xbb] = new Instruction(_12x); // add-long/2addr
        iset[0xbc] = new Instruction(_12x); // sub-long/2addr
        iset[0xbd] = new Instruction(_12x); // mul-long/2addr
        iset[0xbe] = new Instruction(_12x); // div-long/2addr
        iset[0xbf] = new Instruction(_12x); // rem-long/2addr
        iset[0xc0] = new Instruction(_12x); // and-long/2addr
        iset[0xc1] = new Instruction(_12x); // or-long/2addr
        iset[0xc2] = new Instruction(_12x); // xor-long/2addr
        iset[0xc3] = new Instruction(_12x); // shl-long/2addr
        iset[0xc4] = new Instruction(_12x); // shr-long/2addr
        iset[0xc5] = new Instruction(_12x); // ushr-long/2addr
        iset[0xc6] = new Instruction(_12x); // add-float/2addr
        iset[0xc7] = new Instruction(_12x); // sub-float/2addr
        iset[0xc8] = new Instruction(_12x); // mul-float/2addr
        iset[0xc9] = new Instruction(_12x); // div-float/2addr
        iset[0xca] = new Instruction(_12x); // rem-float/2addr
        iset[0xcb] = new Instruction(_12x); // add-double/2addr
        iset[0xcc] = new Instruction(_12x); // sub-double/2addr
        iset[0xcd] = new Instruction(_12x); // mul-double/2addr
        iset[0xce] = new Instruction(_12x); // div-double/2addr
        iset[0xcf] = new Instruction(_12x); // rem-double/2addr 	A: destination and first source register or pair (4 bits)
        //d0..d7 22s 	binop/lit16 vA, vB, #+CCCC
        iset[0xd0] = new Instruction(_22s); // add-int/lit16
        iset[0xd1] = new Instruction(_22s); // rsub-int (reverse subtract)
        iset[0xd2] = new Instruction(_22s); // mul-int/lit16
        iset[0xd3] = new Instruction(_22s); // div-int/lit16
        iset[0xd4] = new Instruction(_22s); // rem-int/lit16
        iset[0xd5] = new Instruction(_22s); // and-int/lit16
        iset[0xd6] = new Instruction(_22s); // or-int/lit16
        iset[0xd7] = new Instruction(_22s); // xor-int/lit16 	A: destination register (4 bits)
        //d8..e2 22b 	binop/lit8 vAA, vBB, #+CC
        iset[0xd8] = new Instruction(_22b); // add-int/lit8
        iset[0xd9] = new Instruction(_22b); // rsub-int/lit8
        iset[0xda] = new Instruction(_22b); // mul-int/lit8
        iset[0xdb] = new Instruction(_22b); // div-int/lit8
        iset[0xdc] = new Instruction(_22b); // rem-int/lit8
        iset[0xdd] = new Instruction(_22b); // and-int/lit8
        iset[0xde] = new Instruction(_22b); // or-int/lit8
        iset[0xdf] = new Instruction(_22b); // xor-int/lit8
        iset[0xe0] = new Instruction(_22b); // shl-int/lit8
        iset[0xe1] = new Instruction(_22b); // shr-int/lit8
        iset[0xe2] = new Instruction(_22b); // ushr-int/lit8 	A: destination register (8 bits)
        //e3..ff 10x 	(unused) 	  	(unused)
    }
    
}
