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
    private Function func;

    private int a()   {return code  >>  6 & 0b0_1111_1111;} //  8 bits
    private int c()   {return code  >> 14 & 0b1_1111_1111;} //  9 bits
    private int b()   {return code  >> 23 & 0b1_1111_1111;} //  9 bits
    private int ax()  {return code >>>  6;                } // 26 bits
    private int bx()  {return code >>> 14;                } // 18 bits
    private int sbx() {return code  >> 14;                } // 18 bits & signed

    @Override
    protected void readContent(LuacOutReader reader) {
        code = reader.readInt();
        opcode = OpCode.values()[code & 0b11_1111];
        super.setName(opcode.name());
    }

    public void expandOperands(Function func) {
        this.func = func;
        addKid(opcode.description);
        switch (opcode) {
            case OP_CALL:
                expandCall();
                break;
            case OP_RETURN:
                expandReturn();
                break;
            default:
                expandR("R(A)", a());
                expandR("R(B)", b());
                expandR("R(C)", c());
                expandRK("RK(B)", b());
                expandRK("RK(C)", c());
                expandUpValue("UpValue[A]", a());
                expandUpValue("UpValue[B]", b());
                expandKstBx();
                break;
        }
    }

    // R(A), ... ,R(A+C-2) := R(A)(R(A+1), ... ,R(A+B-1))
    private void expandCall() {
        int a = a();
        int b = b();
        int c = c();

        addKid("A => " + a);
        addKid("B => " + b);
        addKid("C => " + c);
    }

    // return R(A), ... ,R(A+B-2)
    private void expandReturn() {
        int a = a();
        int b = b();

        addKid("A => " + a);
        addKid("B => " + b);
    }

    private void expandR(String rx, int x) {
        if (opcode.description.contains(rx)) {
            rx += " => R(" + x + ")";
            rx += " => " + func.getLocVarName(x);
            addKid(rx);
        }
    }

    private void expandRK(String rkx, int x) {
        if (opcode.description.contains(rkx)) {
            rkx += " => RK(" + x + ")";

            if (x > 0xFF) { // constant
                rkx += " => Kst(" + (x & 0xFF) + ")";
                rkx += " => " + func.getConstant(x & 0xFF).getDesc();
            } else { // register
                rkx += " => R(" + x + ")";
                rkx += " => " + func.getLocVarName(x);
            }

            addKid(rkx);
        }
    }

    private void expandUpValue(String uvx, int x) {
        if (opcode.description.contains(uvx)) {
            uvx += " => UpValue[" + x + "]";
            addKid(uvx);
        }
    }

    private void expandKstBx() {
        if (opcode.description.contains("Kst(Bx)")) {
            int bx = bx();
            String kstBx = "Kst(Bx) => Kst(" + bx + ")";
            kstBx += " => " +func.getConstant(bx).getDesc();
            addKid(kstBx);
        }
    }

    private void addKid(String name) {
        super.add(name, new LuacOutComponent());
    }

}
