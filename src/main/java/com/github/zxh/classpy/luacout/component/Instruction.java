package com.github.zxh.classpy.luacout.component;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;
import com.github.zxh.classpy.luacout.lvm.OpCode;

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
public class Instruction extends LuacOutComponent {

    private int code;
    private OpCode opcode;

    private int a()   {return code  >>  6 & 0b0_1111_1111;} //  8 bits
    private int c()   {return code  >> 14 & 0b1_1111_1111;} //  9 bits
    private int b()   {return code  >> 23 & 0b1_1111_1111;} //  9 bits
    private int ax()  {return code >>>  6;                } // 26 bits
    private int bx()  {return code >>> 14;                } // 18 bits
    private int sBx() {return code  >> 14;                } // 18 bits & signed

    @Override
    protected void readContent(LuacOutReader reader) {
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

        long line = debug.getLine(pc);
        String desc = String.format("\t[%s]\t%s\t", line > 0 ? line : "-", opcode);

        switch (opcode.mode) {
            case iABC:
//                self.printf("%d", a)
//                if i.BMode() != OpArgN {
//                if isK(b) {
//                    self.printf(" %d", myk(indexK(b)))
//                } else {
//                    self.printf(" %d", b)
//                }
//            }
//            if i.CMode() != OpArgN {
//                if isK(c) {
//                    self.printf(" %d", myk(indexK(c)))
//                } else {
//                    self.printf(" %d", c)
//                }
//            }
                break;
            case iABx:
//                self.printf("%d", a)
//                if i.BMode() == OpArgK {
//                self.printf(" %d", myk(bx))
//            }
//            if i.BMode() == OpArgU {
//                self.printf(" %d", bx)
//            }
                break;
            case iAsBx:
//                self.printf("%d %d", a, sbx)
                break;
            case iAx:
                break;
//                self.printf("%d", myk(ax))
        }

        switch (opcode) {
            case OP_LOADK:
//                self.printf("\t; ")
//                self.printConstant(f, bx)
                break;
            case OP_GETUPVAL:
            case OP_SETUPVAL:
//            self.printf("\t; %s", upvalName(f, b))
                break;
            case OP_GETTABUP:
//                self.printf("\t; %s", upvalName(f, b))
//                if isK(c) {
//                self.printf(" ")
//                self.printConstant(f, indexK(c))
//            }
                break;
            case OP_SETTABUP:
//                self.printf("\t; %s", upvalName(f, a))
//                if isK(b) {
//                self.printf(" ")
//                self.printConstant(f, indexK(b))
//            }
//            if isK(c) {
//                self.printf(" ")
//                self.printConstant(f, indexK(c))
//            }
                break;
            case OP_GETTABLE:
            case OP_SELF:
//                if isK(c) {
//                self.printf("\t; ")
//                self.printConstant(f, indexK(c))
//            }
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
//                if isK(b) || isK(c) {
//                self.printf("\t; ")
//                if isK(b) {
//                    self.printConstant(f, indexK(b))
//                } else {
//                    self.printf("-")
//                }
//                self.printf(" ")
//                if isK(c) {
//                    self.printConstant(f, indexK(c))
//                } else {
//                    self.printf("-")
//                }
//            }
                break;
            case OP_JMP:
            case OP_FORLOOP:
            case OP_FORPREP:
            case OP_TFORLOOP:
//                self.printf("\t; to %d", sbx+pc+2)
                break;
            case OP_CLOSURE:
//                // self.printf("\t; %p",VOID(f->p[bx]));
                break;
            case OP_SETLIST:
//                if c == 0 {
//                // self.printf("\t; %d",(int)code[++pc]);
//                panic("todo!")
//            } else {
//                self.printf("\t; %d", c)
//            }
                break;
            case OP_EXTRAARG:
//                self.printf("\t; ")
//                self.printConstant(f, ax)
                break;
        }

        super.setDesc(desc);
    }

}
