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
    
    private static final InstructionInfo[] iset = new InstructionInfo[256];
    static {
        iset[0x00] = new InstructionInfo(_10x, "nop");
        iset[0x01] = new InstructionInfo(_12x, "move vA, vB");
        iset[0x02] = new InstructionInfo(_22x, "move/from16 vAA, vBBBB");
        iset[0x03] = new InstructionInfo(_32x, "move/16 vAAAA, vBBBB");
        iset[0x04] = new InstructionInfo(_12x, "move-wide vA, vB");
        iset[0x05] = new InstructionInfo(_22x, "move-wide/from16 vAA, vBBBB");
        iset[0x06] = new InstructionInfo(_32x, "move-wide/16 vAAAA, vBBBB");
        iset[0x07] = new InstructionInfo(_12x, "move-object vA, vB");
        iset[0x08] = new InstructionInfo(_22x, "move-object/from16 vAA, vBBBB");
        iset[0x09] = new InstructionInfo(_32x, "move-object/16 vAAAA, vBBBB");
        iset[0x0a] = new InstructionInfo(_11x, "move-result vAA");
        iset[0x0b] = new InstructionInfo(_11x, "move-result-wide vAA");
        iset[0x0c] = new InstructionInfo(_11x, "move-result-object vAA");
        iset[0x0d] = new InstructionInfo(_11x, "move-exception vAA");
        iset[0x0e] = new InstructionInfo(_10x, "return-void");
        iset[0x0f] = new InstructionInfo(_11x, "return vAA");
        iset[0x10] = new InstructionInfo(_11x, "return-wide vAA");
        iset[0x11] = new InstructionInfo(_11x, "return-object vAA");
        iset[0x12] = new InstructionInfo(_11n, "const/4 vA, #+B");
        iset[0x13] = new InstructionInfo(_21s, "const/16 vAA, #+BBBB");
        iset[0x14] = new InstructionInfo(_31i, "const vAA, #+BBBBBBBB");
        iset[0x15] = new InstructionInfo(_21h, "const/high16 vAA, #+BBBB0000");
        iset[0x16] = new InstructionInfo(_21s, "const-wide/16 vAA, #+BBBB");
        iset[0x17] = new InstructionInfo(_31i, "const-wide/32 vAA, #+BBBBBBBB");
        iset[0x18] = new InstructionInfo(_51l, "const-wide vAA, #+BBBBBBBBBBBBBBBB");
        iset[0x19] = new InstructionInfo(_21h, "const-wide/high16 vAA, #+BBBB000000000000");
        iset[0x1a] = new InstructionInfo(_21c, "const-string vAA, string@BBBB");
        iset[0x1b] = new InstructionInfo(_31c, "const-string/jumbo vAA, string@BBBBBBBB");
        iset[0x1c] = new InstructionInfo(_21c, "const-class vAA, type@BBBB");
        iset[0x1d] = new InstructionInfo(_11x, "monitor-enter vAA");
        iset[0x1e] = new InstructionInfo(_11x, "monitor-exit vAA");
        iset[0x1f] = new InstructionInfo(_21c, "check-cast vAA, type@BBBB");
        iset[0x20] = new InstructionInfo(_22c, "instance-of vA, vB, type@CCCC");
        iset[0x21] = new InstructionInfo(_12x, "array-length vA, vB");
        iset[0x22] = new InstructionInfo(_21c, "new-instance vAA, type@BBBB");
        iset[0x23] = new InstructionInfo(_22c, "new-array vA, vB, type@CCCC");
        iset[0x24] = new InstructionInfo(_35c, "filled-new-array {vC, vD, vE, vF, vG}, type@BBBB");
        iset[0x25] = new InstructionInfo(_3rc, "filled-new-array/range {vCCCC .. vNNNN}, type@BBBB");
        iset[0x26] = new InstructionInfo(_31t, "fill-array-data vAA, +BBBBBBBB");
        iset[0x27] = new InstructionInfo(_11x, "throw vAA");
        iset[0x28] = new InstructionInfo(_10t, "goto +AA");
        iset[0x29] = new InstructionInfo(_20t, "goto/16 +AAAA");
        iset[0x2a] = new InstructionInfo(_30t, "goto/32 +AAAAAAAA");
        iset[0x2b] = new InstructionInfo(_31t, "packed-switch vAA, +BBBBBBBB");
        iset[0x2c] = new InstructionInfo(_31t, "sparse-switch vAA, +BBBBBBBB");
        //2d..31 23x 	cmpkind vAA, vBB, vCC
        iset[0x2d] = new InstructionInfo(_23x, "cmpl-float (lt bias)");
        iset[0x2e] = new InstructionInfo(_23x, "cmpg-float (gt bias)");
        iset[0x2f] = new InstructionInfo(_23x, "cmpl-double (lt bias)");
        iset[0x30] = new InstructionInfo(_23x, "cmpg-double (gt bias)");
        iset[0x31] = new InstructionInfo(_23x, "cmp-long");
        //32..37 22t 	if-test vA, vB, +CCCC
        iset[0x32] = new InstructionInfo(_22t, "if-eq");
        iset[0x33] = new InstructionInfo(_22t, "if-ne");
        iset[0x34] = new InstructionInfo(_22t, "if-lt");
        iset[0x35] = new InstructionInfo(_22t, "if-ge");
        iset[0x36] = new InstructionInfo(_22t, "if-gt");
        iset[0x37] = new InstructionInfo(_22t, "if-le");
        //38..3d 21t 	if-testz vAA, +BBBB
        iset[0x38] = new InstructionInfo(_21t, "if-eqz");
        iset[0x39] = new InstructionInfo(_21t, "if-nez");
        iset[0x3a] = new InstructionInfo(_21t, "if-ltz");
        iset[0x3b] = new InstructionInfo(_21t, "if-gez");
        iset[0x3c] = new InstructionInfo(_21t, "if-gtz");
        iset[0x3d] = new InstructionInfo(_21t, "if-lez");
        //3e..43 10x 	(unused) 	  	(unused)
        //44..51 23x 	arrayop vAA, vBB, vCC
        iset[0x44] = new InstructionInfo(_23x, "aget");
        iset[0x45] = new InstructionInfo(_23x, "aget-wide");
        iset[0x46] = new InstructionInfo(_23x, "aget-object");
        iset[0x47] = new InstructionInfo(_23x, "aget-boolean");
        iset[0x48] = new InstructionInfo(_23x, "aget-byte");
        iset[0x49] = new InstructionInfo(_23x, "aget-char");
        iset[0x4a] = new InstructionInfo(_23x, "aget-short");
        iset[0x4b] = new InstructionInfo(_23x, "aput");
        iset[0x4c] = new InstructionInfo(_23x, "aput-wide");
        iset[0x4d] = new InstructionInfo(_23x, "aput-object");
        iset[0x4e] = new InstructionInfo(_23x, "aput-boolean");
        iset[0x4f] = new InstructionInfo(_23x, "aput-byte");
        iset[0x50] = new InstructionInfo(_23x, "aput-char");
        iset[0x51] = new InstructionInfo(_23x, "aput-short");
        //52..5f 22c 	iinstanceop vA, vB, field@CCCC
        iset[0x52] = new InstructionInfo(_22c, "iget");
        iset[0x53] = new InstructionInfo(_22c, "iget-wide");
        iset[0x54] = new InstructionInfo(_22c, "iget-object");
        iset[0x55] = new InstructionInfo(_22c, "iget-boolean");
        iset[0x56] = new InstructionInfo(_22c, "iget-byte");
        iset[0x57] = new InstructionInfo(_22c, "iget-char");
        iset[0x58] = new InstructionInfo(_22c, "iget-short");
        iset[0x59] = new InstructionInfo(_22c, "iput");
        iset[0x5a] = new InstructionInfo(_22c, "iput-wide");
        iset[0x5b] = new InstructionInfo(_22c, "iput-object");
        iset[0x5c] = new InstructionInfo(_22c, "iput-boolean");
        iset[0x5d] = new InstructionInfo(_22c, "iput-byte");
        iset[0x5e] = new InstructionInfo(_22c, "iput-char");
        iset[0x5f] = new InstructionInfo(_22c, "iput-short");
        //60..6d 21c 	sstaticop vAA, field@BBBB
        iset[0x60] = new InstructionInfo(_21c, "sget");
        iset[0x61] = new InstructionInfo(_21c, "sget-wide");
        iset[0x62] = new InstructionInfo(_21c, "sget-object");
        iset[0x63] = new InstructionInfo(_21c, "sget-boolean");
        iset[0x64] = new InstructionInfo(_21c, "sget-byte");
        iset[0x65] = new InstructionInfo(_21c, "sget-char");
        iset[0x66] = new InstructionInfo(_21c, "sget-short");
        iset[0x67] = new InstructionInfo(_21c, "sput");
        iset[0x68] = new InstructionInfo(_21c, "sput-wide");
        iset[0x69] = new InstructionInfo(_21c, "sput-object");
        iset[0x6a] = new InstructionInfo(_21c, "sput-boolean");
        iset[0x6b] = new InstructionInfo(_21c, "sput-byte");
        iset[0x6c] = new InstructionInfo(_21c, "sput-char");
        iset[0x6d] = new InstructionInfo(_21c, "sput-short");
        //6e..72 35c 	invoke-kind {vC, vD, vE, vF, vG}, meth@BBBB
        iset[0x6e] = new InstructionInfo(_35c, "invoke-virtual");
        iset[0x6f] = new InstructionInfo(_35c, "invoke-super");
        iset[0x70] = new InstructionInfo(_35c, "invoke-direct");
        iset[0x71] = new InstructionInfo(_35c, "invoke-static");
        iset[0x72] = new InstructionInfo(_35c, "invoke-interface");
        //73 10x 	(unused) 	  	(unused)
        //74..78 3rc 	invoke-kind/range {vCCCC .. vNNNN}, meth@BBBB
        iset[0x74] = new InstructionInfo(_3rc, "invoke-virtual/range");
        iset[0x75] = new InstructionInfo(_3rc, "invoke-super/range");
        iset[0x76] = new InstructionInfo(_3rc, "invoke-direct/range");
        iset[0x77] = new InstructionInfo(_3rc, "invoke-static/range");
        iset[0x78] = new InstructionInfo(_3rc, "invoke-interface/range");
        //79..7a 10x 	(unused) 	  	(unused)
        //7b..8f 12x 	unop vA, vB
        iset[0x7b] = new InstructionInfo(_12x, "neg-int");
        iset[0x7c] = new InstructionInfo(_12x, "not-int");
        iset[0x7d] = new InstructionInfo(_12x, "neg-long");
        iset[0x7e] = new InstructionInfo(_12x, "not-long");
        iset[0x7f] = new InstructionInfo(_12x, "neg-float");
        iset[0x80] = new InstructionInfo(_12x, "neg-double");
        iset[0x81] = new InstructionInfo(_12x, "int-to-long");
        iset[0x82] = new InstructionInfo(_12x, "int-to-float");
        iset[0x83] = new InstructionInfo(_12x, "int-to-double");
        iset[0x84] = new InstructionInfo(_12x, "long-to-int");
        iset[0x85] = new InstructionInfo(_12x, "long-to-float");
        iset[0x86] = new InstructionInfo(_12x, "long-to-double");
        iset[0x87] = new InstructionInfo(_12x, "float-to-int");
        iset[0x88] = new InstructionInfo(_12x, "float-to-long");
        iset[0x89] = new InstructionInfo(_12x, "float-to-double");
        iset[0x8a] = new InstructionInfo(_12x, "double-to-int");
        iset[0x8b] = new InstructionInfo(_12x, "double-to-long");
        iset[0x8c] = new InstructionInfo(_12x, "double-to-float");
        iset[0x8d] = new InstructionInfo(_12x, "int-to-byte");
        iset[0x8e] = new InstructionInfo(_12x, "int-to-char");
        iset[0x8f] = new InstructionInfo(_12x, "int-to-short");
        //90..af 23x 	binop vAA, vBB, vCC
        iset[0x90] = new InstructionInfo(_23x, "add-int");
        iset[0x91] = new InstructionInfo(_23x, "sub-int");
        iset[0x92] = new InstructionInfo(_23x, "mul-int");
        iset[0x93] = new InstructionInfo(_23x, "div-int");
        iset[0x94] = new InstructionInfo(_23x, "rem-int");
        iset[0x95] = new InstructionInfo(_23x, "and-int");
        iset[0x96] = new InstructionInfo(_23x, "or-int");
        iset[0x97] = new InstructionInfo(_23x, "xor-int");
        iset[0x98] = new InstructionInfo(_23x, "shl-int");
        iset[0x99] = new InstructionInfo(_23x, "shr-int");
        iset[0x9a] = new InstructionInfo(_23x, "ushr-int");
        iset[0x9b] = new InstructionInfo(_23x, "add-long");
        iset[0x9c] = new InstructionInfo(_23x, "sub-long");
        iset[0x9d] = new InstructionInfo(_23x, "mul-long");
        iset[0x9e] = new InstructionInfo(_23x, "div-long");
        iset[0x9f] = new InstructionInfo(_23x, "rem-long");
        iset[0xa0] = new InstructionInfo(_23x, "and-long");
        iset[0xa1] = new InstructionInfo(_23x, "or-long");
        iset[0xa2] = new InstructionInfo(_23x, "xor-long");
        iset[0xa3] = new InstructionInfo(_23x, "shl-long");
        iset[0xa4] = new InstructionInfo(_23x, "shr-long");
        iset[0xa5] = new InstructionInfo(_23x, "ushr-long");
        iset[0xa6] = new InstructionInfo(_23x, "add-float");
        iset[0xa7] = new InstructionInfo(_23x, "sub-float");
        iset[0xa8] = new InstructionInfo(_23x, "mul-float");
        iset[0xa9] = new InstructionInfo(_23x, "div-float");
        iset[0xaa] = new InstructionInfo(_23x, "rem-float");
        iset[0xab] = new InstructionInfo(_23x, "add-double");
        iset[0xac] = new InstructionInfo(_23x, "sub-double");
        iset[0xad] = new InstructionInfo(_23x, "mul-double");
        iset[0xae] = new InstructionInfo(_23x, "div-double");
        iset[0xaf] = new InstructionInfo(_23x, "rem-double");
        //b0..cf 12x 	binop/2addr vA, vB
        iset[0xb0] = new InstructionInfo(_12x, "add-int/2addr");
        iset[0xb1] = new InstructionInfo(_12x, "sub-int/2addr");
        iset[0xb2] = new InstructionInfo(_12x, "mul-int/2addr");
        iset[0xb3] = new InstructionInfo(_12x, "div-int/2addr");
        iset[0xb4] = new InstructionInfo(_12x, "rem-int/2addr");
        iset[0xb5] = new InstructionInfo(_12x, "and-int/2addr");
        iset[0xb6] = new InstructionInfo(_12x, "or-int/2addr");
        iset[0xb7] = new InstructionInfo(_12x, "xor-int/2addr");
        iset[0xb8] = new InstructionInfo(_12x, "shl-int/2addr");
        iset[0xb9] = new InstructionInfo(_12x, "shr-int/2addr");
        iset[0xba] = new InstructionInfo(_12x, "ushr-int/2addr");
        iset[0xbb] = new InstructionInfo(_12x, "add-long/2addr");
        iset[0xbc] = new InstructionInfo(_12x, "sub-long/2addr");
        iset[0xbd] = new InstructionInfo(_12x, "mul-long/2addr");
        iset[0xbe] = new InstructionInfo(_12x, "div-long/2addr");
        iset[0xbf] = new InstructionInfo(_12x, "rem-long/2addr");
        iset[0xc0] = new InstructionInfo(_12x, "and-long/2addr");
        iset[0xc1] = new InstructionInfo(_12x, "or-long/2addr");
        iset[0xc2] = new InstructionInfo(_12x, "xor-long/2addr");
        iset[0xc3] = new InstructionInfo(_12x, "shl-long/2addr");
        iset[0xc4] = new InstructionInfo(_12x, "shr-long/2addr");
        iset[0xc5] = new InstructionInfo(_12x, "ushr-long/2addr");
        iset[0xc6] = new InstructionInfo(_12x, "add-float/2addr");
        iset[0xc7] = new InstructionInfo(_12x, "sub-float/2addr");
        iset[0xc8] = new InstructionInfo(_12x, "mul-float/2addr");
        iset[0xc9] = new InstructionInfo(_12x, "div-float/2addr");
        iset[0xca] = new InstructionInfo(_12x, "rem-float/2addr");
        iset[0xcb] = new InstructionInfo(_12x, "add-double/2addr");
        iset[0xcc] = new InstructionInfo(_12x, "sub-double/2addr");
        iset[0xcd] = new InstructionInfo(_12x, "mul-double/2addr");
        iset[0xce] = new InstructionInfo(_12x, "div-double/2addr");
        iset[0xcf] = new InstructionInfo(_12x, "rem-double/2addr");
        //d0..d7 22s 	binop/lit16 vA, vB, #+CCCC
        iset[0xd0] = new InstructionInfo(_22s, "add-int/lit16");
        iset[0xd1] = new InstructionInfo(_22s, "rsub-int (reverse subtract)");
        iset[0xd2] = new InstructionInfo(_22s, "mul-int/lit16");
        iset[0xd3] = new InstructionInfo(_22s, "div-int/lit16");
        iset[0xd4] = new InstructionInfo(_22s, "rem-int/lit16");
        iset[0xd5] = new InstructionInfo(_22s, "and-int/lit16");
        iset[0xd6] = new InstructionInfo(_22s, "or-int/lit16");
        iset[0xd7] = new InstructionInfo(_22s, "xor-int/lit16");
        //d8..e2 22b 	binop/lit8 vAA, vBB, #+CC
        iset[0xd8] = new InstructionInfo(_22b, "add-int/lit8");
        iset[0xd9] = new InstructionInfo(_22b, "rsub-int/lit8");
        iset[0xda] = new InstructionInfo(_22b, "mul-int/lit8");
        iset[0xdb] = new InstructionInfo(_22b, "div-int/lit8");
        iset[0xdc] = new InstructionInfo(_22b, "rem-int/lit8");
        iset[0xdd] = new InstructionInfo(_22b, "and-int/lit8");
        iset[0xde] = new InstructionInfo(_22b, "or-int/lit8");
        iset[0xdf] = new InstructionInfo(_22b, "xor-int/lit8");
        iset[0xe0] = new InstructionInfo(_22b, "shl-int/lit8");
        iset[0xe1] = new InstructionInfo(_22b, "shr-int/lit8");
        iset[0xe2] = new InstructionInfo(_22b, "ushr-int/lit8");
        //e3..ff 10x 	(unused) 	  	(unused)
    }
    
    public static InstructionInfo getInstructionInfo(int op) {
        if (iset[op] != null) {
            return iset[op];
        } else {
            throw new FileParseException("Unused op: " + Integer.toHexString(op));
        }
    }
    
    
    public static class InstructionInfo {
        
        public final InstructionFormat format;
        public final String mnemonic;
        public final String simpleMnemonic;

        public InstructionInfo(InstructionFormat format, String mnemonic) {
            this.format = format;
            this.mnemonic = mnemonic;
            simpleMnemonic = mnemonic.replaceAll("\\s+.*", "");
        }
    
    }
    
}
