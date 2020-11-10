package com.github.zxh.classpy.lua54.binarychunk.part;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;
import com.github.zxh.classpy.lua54.vm.OpCode;

/**
 * Lua 5.4 VM instruction.
 31      23      15       7      0
 ┌------┐┌------┐-┌------┐┌-----┐
 |  C:8 ||  B:8 |k|  A:8 || Op:7| iABC
 └------┘└------┘-└------┘└-----┘
 ┌---------------┐┌------┐┌-----┐
 |     Bx:17     ||  A:8 || Op:7| iABx
 └---------------┘└------┘└-----┘
 ┌---------------┐┌------┐┌-----┐
 |    sBx:17     ||  A:8 || Op:7| iAsBx
 └---------------┘└------┘└-----┘
 ┌-----------------------┐┌-----┐
 |          Ax:25        || Op:7| iAx
 └-----------------------┘└-----┘
 ┌-----------------------┐┌-----┐
 |          sJ:25        || Op:7| isJ
 └-----------------------┘└-----┘
 */
public class Instruction extends BinaryChunkPart {

    private int code;
    private OpCode opcode;

    private int a()   {return code  >>  7 & 0xFF;} // 8 bits
    private int k()   {return code  >> 15 & 0x01;} // 1 bit
    private int b()   {return code  >> 16 & 0xFF;} // 8 bits
    private int c()   {return code  >> 24 & 0xFF;} // 8 bits
    private int ax()  {return code >>>  7;       } // 26 bits
    private int sJ()  {return code >>>  7;       } // 26 bits
    private int bx()  {return code >>> 15;       } // 18 bits
    private int sBx() {return code  >> 15;       } // 18 bits & signed

    @Override
    protected void readContent(BinaryChunkReader reader) {
        code = reader.readInt();
        opcode = OpCode.values()[code & 0b111_1111];
        //super.setName(opcode.name());
    }

    public void updateDesc() {
        int a = a();
        int k = k();
        int b = b();
        int c = c();
        int bx = bx();
        int sBx = sBx();
        int ax = ax();
        int sj = sJ();

        StringBuilder desc = new StringBuilder();
        desc.append(opcode.toString().substring(3));
        switch (opcode.mode) {
            case iABC:
                desc.append(String.format("A=%d, k=%d, B=%d, C=%d", a, k, b, c));
                break;
            case iABx:
                desc.append(String.format("A=%d, Bx=%d", a, bx));
                break;
            case iAsBx:
                desc.append(String.format("A=%d, sBx=%d", a, sBx));
                break;
            case iAx:
                desc.append(String.format("Ax=%d", ax));
                break;
            case isJ:
                desc.append(String.format("sJ=%d", sj));
                break;
        }

        super.setDesc(desc.toString());
    }

}
