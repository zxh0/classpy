package com.github.zxh.classpy.dexfile.bytecode;

import com.github.zxh.classpy.common.FileParseException;
import static com.github.zxh.classpy.dexfile.bytecode.InstructionFormat.*;

/**
 *
 * http://source.android.com/devices/tech/dalvik/dalvik-bytecode.html
 * 
 * @author pc
 */
public class InstructionSet {
    
    private static final InstructionFormat[] iset = new InstructionFormat[256];
    static {
        iset[0x00] = _10x; // nop
        iset[0x01] = _12x; // move vA, vB
        iset[0x02] = _22x; // move/from16 vAA, vBBBB
        iset[0x03] = _32x; // move/16 vAAAA, vBBBB
        iset[0x04] = _12x; // move-wide vA, vB
        iset[0x05] = _22x; // move-wide/from16 vAA, vBBBB
        iset[0x06] = _32x; // move-wide/16 vAAAA, vBBBB
        iset[0x07] = _12x; // move-object vA, vB
        iset[0x08] = _22x; // move-object/from16 vAA, vBBBB
        iset[0x09] = _32x; // move-object/16 vAAAA, vBBBB
        iset[0x0a] = _11x; // move-result vAA
        iset[0x0b] = _11x; // move-result-wide vAA
        iset[0x0c] = _11x; // move-result-object vAA
        iset[0x0d] = _11x; // move-exception vAA
        iset[0x0e] = _10x; // return-void
        iset[0x0f] = _11x; // return vAA
        iset[0x10] = _11x; // return-wide vAA
        iset[0x11] = _11x; // return-object vAA
        iset[0x12] = _11n; // const/4 vA, #+B
        iset[0x13] = _21s; // const/16 vAA, #+BBBB
        iset[0x14] = _31i; // const vAA, #+BBBBBBBB
        iset[0x15] = _21h; // const/high16 vAA, #+BBBB0000
        iset[0x16] = _21s; // const-wide/16 vAA, #+BBBB
        iset[0x17] = _31i; // const-wide/32 vAA, #+BBBBBBBB
        iset[0x18] = _51l; // const-wide vAA, #+BBBBBBBBBBBBBBBB
        iset[0x19] = _21h; // const-wide/high16 vAA, #+BBBB000000000000
        iset[0x1a] = _21c; // const-string vAA, string@BBBB
        iset[0x1b] = _31c; // const-string/jumbo vAA, string@BBBBBBBB
        iset[0x1c] = _21c; // const-class vAA, type@BBBB
        iset[0x1d] = _11x; // monitor-enter vAA
        iset[0x1e] = _11x; // monitor-exit vAA
        iset[0x1f] = _21c; // check-cast vAA, type@BBBB
        iset[0x20] = _22c; // instance-of vA, vB, type@CCCC
        iset[0x21] = _12x; // array-length vA, vB
        iset[0x22] = _21c; // new-instance vAA, type@BBBB
        iset[0x23] = _22c; // new-array vA, vB, type@CCCC
        iset[0x24] = _35c; // filled-new-array {vC, vD, vE, vF, vG}, type@BBBB
        iset[0x25] = _3rc; // filled-new-array/range {vCCCC .. vNNNN}, type@BBBB
        iset[0x26] = _31t; // fill-array-data vAA, +BBBBBBBB
        iset[0x27] = _11x; // throw vAA
        iset[0x28] = _10t; // goto +AA
        iset[0x29] = _20t; // goto/16 +AAAA
        iset[0x2a] = _30t; // goto/32 +AAAAAAAA
        iset[0x2b] = _31t; // packed-switch vAA, +BBBBBBBB
        iset[0x2c] = _31t; // sparse-switch vAA, +BBBBBBBB
        //2d..31 23x 	cmpkind vAA, vBB, vCC
        iset[0x2d] = _23x; // cmpl-float (lt bias)
        iset[0x2e] = _23x; // cmpg-float (gt bias)
        iset[0x2f] = _23x; // cmpl-double (lt bias)
        iset[0x30] = _23x; // cmpg-double (gt bias)
        iset[0x31] = _23x; // cmp-long
        //32..37 22t 	if-test vA, vB, +CCCC
        iset[0x32] = _22t; // if-eq
        iset[0x33] = _22t; // if-ne
        iset[0x34] = _22t; // if-lt
        iset[0x35] = _22t; // if-ge
        iset[0x36] = _22t; // if-gt
        iset[0x37] = _22t; // if-le
        //38..3d 21t 	if-testz vAA, +BBBB
        iset[0x38] = _21t; // if-eqz
        iset[0x39] = _21t; // if-nez
        iset[0x3a] = _21t; // if-ltz
        iset[0x3b] = _21t; // if-gez
        iset[0x3c] = _21t; // if-gtz
        iset[0x3d] = _21t; // if-lez
        //3e..43 10x 	(unused) 	  	(unused)
        //44..51 23x 	arrayop vAA, vBB, vCC
        iset[0x44] = _23x; // aget
        iset[0x45] = _23x; // aget-wide
        iset[0x46] = _23x; // aget-object
        iset[0x47] = _23x; // aget-boolean
        iset[0x48] = _23x; // aget-byte
        iset[0x49] = _23x; // aget-char
        iset[0x4a] = _23x; // aget-short
        iset[0x4b] = _23x; // aput
        iset[0x4c] = _23x; // aput-wide
        iset[0x4d] = _23x; // aput-object
        iset[0x4e] = _23x; // aput-boolean
        iset[0x4f] = _23x; // aput-byte
        iset[0x50] = _23x; // aput-char
        iset[0x51] = _23x; // aput-short
        //52..5f 22c 	iinstanceop vA, vB, field@CCCC
        iset[0x52] = _22c; // iget
        iset[0x53] = _22c; // iget-wide
        iset[0x54] = _22c; // iget-object
        iset[0x55] = _22c; // iget-boolean
        iset[0x56] = _22c; // iget-byte
        iset[0x57] = _22c; // iget-char
        iset[0x58] = _22c; // iget-short
        iset[0x59] = _22c; // iput
        iset[0x5a] = _22c; // iput-wide
        iset[0x5b] = _22c; // iput-object
        iset[0x5c] = _22c; // iput-boolean
        iset[0x5d] = _22c; // iput-byte
        iset[0x5e] = _22c; // iput-char
        iset[0x5f] = _22c; // iput-short
        //60..6d 21c 	sstaticop vAA, field@BBBB
        iset[0x60] = _21c; // sget
        iset[0x61] = _21c; // sget-wide
        iset[0x62] = _21c; // sget-object
        iset[0x63] = _21c; // sget-boolean
        iset[0x64] = _21c; // sget-byte
        iset[0x65] = _21c; // sget-char
        iset[0x66] = _21c; // sget-short
        iset[0x67] = _21c; // sput
        iset[0x68] = _21c; // sput-wide
        iset[0x69] = _21c; // sput-object
        iset[0x6a] = _21c; // sput-boolean
        iset[0x6b] = _21c; // sput-byte
        iset[0x6c] = _21c; // sput-char
        iset[0x6d] = _21c; // sput-short
        //6e..72 35c 	invoke-kind {vC, vD, vE, vF, vG}, meth@BBBB
        iset[0x6e] = _35c; // invoke-virtual
        iset[0x6f] = _35c; // invoke-super
        iset[0x70] = _35c; // invoke-direct
        iset[0x71] = _35c; // invoke-static
        iset[0x72] = _35c; // invoke-interface
        //73 10x 	(unused) 	  	(unused)
        //74..78 3rc 	invoke-kind/range {vCCCC .. vNNNN}, meth@BBBB
        iset[0x74] = _3rc; // invoke-virtual/range
        iset[0x75] = _3rc; // invoke-super/range
        iset[0x76] = _3rc; // invoke-direct/range
        iset[0x77] = _3rc; // invoke-static/range
        iset[0x78] = _3rc; // invoke-interface/range
        //79..7a 10x 	(unused) 	  	(unused)
        //7b..8f 12x 	unop vA, vB
        iset[0x7b] = _12x; // neg-int
        iset[0x7c] = _12x; // not-int
        iset[0x7d] = _12x; // neg-long
        iset[0x7e] = _12x; // not-long
        iset[0x7f] = _12x; // neg-float
        iset[0x80] = _12x; // neg-double
        iset[0x81] = _12x; // int-to-long
        iset[0x82] = _12x; // int-to-float
        iset[0x83] = _12x; // int-to-double
        iset[0x84] = _12x; // long-to-int
        iset[0x85] = _12x; // long-to-float
        iset[0x86] = _12x; // long-to-double
        iset[0x87] = _12x; // float-to-int
        iset[0x88] = _12x; // float-to-long
        iset[0x89] = _12x; // float-to-double
        iset[0x8a] = _12x; // double-to-int
        iset[0x8b] = _12x; // double-to-long
        iset[0x8c] = _12x; // double-to-float
        iset[0x8d] = _12x; // int-to-byte
        iset[0x8e] = _12x; // int-to-char
        iset[0x8f] = _12x; // int-to-short
        //90..af 23x 	binop vAA, vBB, vCC
        iset[0x90] = _23x; // add-int
        iset[0x91] = _23x; // sub-int
        iset[0x92] = _23x; // mul-int
        iset[0x93] = _23x; // div-int
        iset[0x94] = _23x; // rem-int
        iset[0x95] = _23x; // and-int
        iset[0x96] = _23x; // or-int
        iset[0x97] = _23x; // xor-int
        iset[0x98] = _23x; // shl-int
        iset[0x99] = _23x; // shr-int
        iset[0x9a] = _23x; // ushr-int
        iset[0x9b] = _23x; // add-long
        iset[0x9c] = _23x; // sub-long
        iset[0x9d] = _23x; // mul-long
        iset[0x9e] = _23x; // div-long
        iset[0x9f] = _23x; // rem-long
        iset[0xa0] = _23x; // and-long
        iset[0xa1] = _23x; // or-long
        iset[0xa2] = _23x; // xor-long
        iset[0xa3] = _23x; // shl-long
        iset[0xa4] = _23x; // shr-long
        iset[0xa5] = _23x; // ushr-long
        iset[0xa6] = _23x; // add-float
        iset[0xa7] = _23x; // sub-float
        iset[0xa8] = _23x; // mul-float
        iset[0xa9] = _23x; // div-float
        iset[0xaa] = _23x; // rem-float
        iset[0xab] = _23x; // add-double
        iset[0xac] = _23x; // sub-double
        iset[0xad] = _23x; // mul-double
        iset[0xae] = _23x; // div-double
        iset[0xaf] = _23x; // rem-double
        //b0..cf 12x 	binop/2addr vA, vB
        iset[0xb0] = _12x; // add-int/2addr
        iset[0xb1] = _12x; // sub-int/2addr
        iset[0xb2] = _12x; // mul-int/2addr
        iset[0xb3] = _12x; // div-int/2addr
        iset[0xb4] = _12x; // rem-int/2addr
        iset[0xb5] = _12x; // and-int/2addr
        iset[0xb6] = _12x; // or-int/2addr
        iset[0xb7] = _12x; // xor-int/2addr
        iset[0xb8] = _12x; // shl-int/2addr
        iset[0xb9] = _12x; // shr-int/2addr
        iset[0xba] = _12x; // ushr-int/2addr
        iset[0xbb] = _12x; // add-long/2addr
        iset[0xbc] = _12x; // sub-long/2addr
        iset[0xbd] = _12x; // mul-long/2addr
        iset[0xbe] = _12x; // div-long/2addr
        iset[0xbf] = _12x; // rem-long/2addr
        iset[0xc0] = _12x; // and-long/2addr
        iset[0xc1] = _12x; // or-long/2addr
        iset[0xc2] = _12x; // xor-long/2addr
        iset[0xc3] = _12x; // shl-long/2addr
        iset[0xc4] = _12x; // shr-long/2addr
        iset[0xc5] = _12x; // ushr-long/2addr
        iset[0xc6] = _12x; // add-float/2addr
        iset[0xc7] = _12x; // sub-float/2addr
        iset[0xc8] = _12x; // mul-float/2addr
        iset[0xc9] = _12x; // div-float/2addr
        iset[0xca] = _12x; // rem-float/2addr
        iset[0xcb] = _12x; // add-double/2addr
        iset[0xcc] = _12x; // sub-double/2addr
        iset[0xcd] = _12x; // mul-double/2addr
        iset[0xce] = _12x; // div-double/2addr
        iset[0xcf] = _12x; // rem-double/2addr
        //d0..d7 22s 	binop/lit16 vA, vB, #+CCCC
        iset[0xd0] = _22s; // add-int/lit16
        iset[0xd1] = _22s; // rsub-int (reverse subtract)
        iset[0xd2] = _22s; // mul-int/lit16
        iset[0xd3] = _22s; // div-int/lit16
        iset[0xd4] = _22s; // rem-int/lit16
        iset[0xd5] = _22s; // and-int/lit16
        iset[0xd6] = _22s; // or-int/lit16
        iset[0xd7] = _22s; // xor-int/lit16
        //d8..e2 22b 	binop/lit8 vAA, vBB, #+CC
        iset[0xd8] = _22b; // add-int/lit8
        iset[0xd9] = _22b; // rsub-int/lit8
        iset[0xda] = _22b; // mul-int/lit8
        iset[0xdb] = _22b; // div-int/lit8
        iset[0xdc] = _22b; // rem-int/lit8
        iset[0xdd] = _22b; // and-int/lit8
        iset[0xde] = _22b; // or-int/lit8
        iset[0xdf] = _22b; // xor-int/lit8
        iset[0xe0] = _22b; // shl-int/lit8
        iset[0xe1] = _22b; // shr-int/lit8
        iset[0xe2] = _22b; // ushr-int/lit8
        //e3..ff 10x 	(unused) 	  	(unused)
    }
    
    public static InstructionFormat getFormat(int op) {
        if (iset[op] != null) {
            return iset[op];
        } else {
            throw new FileParseException("Unused op: " + op);
        }
    }
    
}
