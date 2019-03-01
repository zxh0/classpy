package com.github.zxh.classpy.lua.binarychunk.component;

import com.github.zxh.classpy.lua.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;
import com.github.zxh.classpy.lua.vm.OpArgMask;
import com.github.zxh.classpy.lua.vm.OpCode;

/**
 * LuaVM instruction.
 * 31       22       13       5    0
 *  +-------+^------+-^-----+-^-----
 *  |b=9bits |c=9bits |a=8bits|opc6|
 *  +-------+^------+-^-----+-^-----
 *  |    bx=18bits    |a=8bits|opc6|
 *  +-------+^------+-^-----+-^-----
 *  |   sbx=18bits    |a=8bits|opc6|
 *  +-------+^------+-^-----+-^-----
 *  |    ax=26bits            |opc6|
 *  +-------+^------+-^-----+-^-----
 * 31      23      15       7      0
 */
public class Instruction extends BinaryChunkPart {

    private int code;
    private OpCode opcode;

    private int a()   {return code  >>  6 & 0b0_1111_1111;} //  8 bits
    private int c()   {return code  >> 14 & 0b1_1111_1111;} //  9 bits
    private int b()   {return code  >> 23 & 0b1_1111_1111;} //  9 bits
    private int ax()  {return code >>>  6;                } // 26 bits
    private int bx()  {return code >>> 14;                } // 18 bits
    private int sBx() {return code  >> 14;                } // 18 bits & signed

    @Override
    protected void readContent(BinaryChunkReader reader) {
        code = reader.readInt();
        opcode = OpCode.values()[code & 0b11_1111];
        //super.setName(opcode.name());
    }

    public void setDesc(int pc, Function func, Debug debug) {
        int a = a();
        int b = b();
        int c = c();
        int bx = bx();
        int sBx = sBx();
        int ax = ax();

        StringBuilder desc = new StringBuilder();

        long line = debug.getLine(pc);
        desc.append(String.format("\t  [%s]\t%-12s\t",
                line > 0 ? line : "-",
                opcode.toString().substring(3)));

        switch (opcode.mode) {
            case iABC:
                desc.append(a);
                if (opcode.b != OpArgMask.OpArgN) {
                    if (isK(b)) {
                        desc.append(" ").append(myk(indexK(b)));
                    } else {
                        desc.append(" ").append(b);
                    }
                }
                if (opcode.c != OpArgMask.OpArgN) {
                    if (isK(c)) {
                        desc.append(" ").append(myk(indexK(c)));
                    } else {
                        desc.append(" ").append(c);
                    }
                }
                break;
            case iABx:
                desc.append(a);
                if (opcode.b == OpArgMask.OpArgK) {
                    desc.append(" ").append(myk(bx));
                } else if (opcode.b == OpArgMask.OpArgU) {
                    desc.append(" ").append(bx);
                }
                break;
            case iAsBx:
                desc.append(a).append(" ").append(sBx);
                break;
            case iAx:
                desc.append(myk(ax));
                break;
        }

        switch (opcode) {
            case OP_LOADK:
                desc.append("\t; ").append(func.getConstant(bx));
                break;
            case OP_GETUPVAL:
            case OP_SETUPVAL:
                desc.append("\t; ").append(debug.getUpvalName(b));
                break;
            case OP_GETTABUP:
                desc.append("\t; ").append(debug.getUpvalName(b));
                if (isK(c)) {
                    desc.append(" ").append(func.getConstant(indexK(c)));
                }
                break;
            case OP_SETTABUP:
                desc.append("\t; ").append(debug.getUpvalName(a));
                if (isK(b)) {
                    desc.append(" ").append(func.getConstant(indexK(b)));
                }
                if (isK(c)) {
                    desc.append(" ").append(func.getConstant(indexK(b)));
                }
                break;
            case OP_GETTABLE:
            case OP_SELF:
                if (isK(c)) {
                    desc.append("\t; ").append(func.getConstant(indexK(c)));
                }
                break;
            case OP_SETTABLE:
            case OP_ADD:
            case OP_SUB:
            case OP_MUL:
            case OP_POW:
            case OP_DIV:
            case OP_IDIV:
            case OP_BAND:
            case OP_BOR:
            case OP_BXOR:
            case OP_SHL:
            case OP_SHR:
            case OP_EQ:
            case OP_LT:
            case OP_LE:
                if (isK(b) || isK(c)) {
                    desc.append("\t; ");
                    if (isK(b)) {
                        desc.append(func.getConstant(indexK(b)));
                    } else {
                        desc.append("-");
                    }
                    desc.append("-");
                    if (isK(c)) {
                        desc.append(func.getConstant(indexK(c)));
                    } else {
                        desc.append("-");
                    }
                }
                break;
            case OP_JMP:
            case OP_FORLOOP:
            case OP_FORPREP:
            case OP_TFORLOOP:
                desc.append("\t; to ").append(sBx + pc + 2);
                break;
            case OP_CLOSURE:
//                // self.printf("\t; %p",VOID(f->p[bx]));
                break;
            case OP_SETLIST:
                if (c == 0) {
//                // self.printf("\t; %d",(int)code[++pc]);
//                panic("todo!")
                } else {
                    desc.append("\t; ").append(c);
                }
                break;
            case OP_EXTRAARG:
                desc.append("\t; ").append(func.getConstant(ax));
                break;
        }

        super.setDesc(desc.toString());
    }

    private boolean isK(int x) {
        return (x & (1 << 8)) != 0;
    }

    private int indexK(int r) {
        return r & ~(1 << 8);
    }

    private int myk(int x) {
        return -1 - x;
    }

}
