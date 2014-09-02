package com.github.zxh.classpy.dexfile.bytecode;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.bytecode.InstructionSet.InstructionInfo;

/**
 *
 * @author zxh
 */
public class Instruction extends DexComponent {

    @Override
    protected void readContent(DexReader reader) {
        int opcode = reader.readUByte();
        int operand;
        int a, b, aa, bb, cc, aaaa, bbbb, cccc;
        int aaaaLo, aaaaHi;
        
        InstructionInfo insnInfo = InstructionSet.getInstructionInfo(opcode);
        switch (insnInfo.format) {
            case _00x:
                reader.readByte();
                setName(insnInfo.simpleMnemonic);
                break;
            case _10x: // op
                reader.readByte();
                setName(insnInfo.simpleMnemonic);
                break;
            case _12x: // op vA, vB
                operand = reader.readUByte();
                a = operand & 0b1111;
                b = operand >> 4;
                setName(insnInfo.simpleMnemonic + " v" + a + ", v" + b);
                break;
            case _11n: // const/4 vA, #+B
                operand = reader.readUByte();
                a = operand & 0b1111;
                b = operand >> 4; // todo
                setName(insnInfo.simpleMnemonic + " v" + a + ", #+" + b);
                break;
            case _11x: // op vAA
                aa = reader.readUByte();
                setName(insnInfo.simpleMnemonic + " v" + aa);
                break;
            case _10t: // op +AA
                aa = reader.readByte();
                setName(insnInfo.simpleMnemonic + " +" + aa);
                break;
            case _20t: // op +AAAA
                reader.readByte();
                aaaa = reader.readShort();
                setName(insnInfo.simpleMnemonic + " +" + aaaa);
                break;
            case _20bc: // op AA, kind@BBBB
                // todo
                reader.readByte();
                reader.readShort();
                setName(insnInfo.simpleMnemonic);
                break;
            case _22x: // op vAA, vBBBB
                aa = reader.readUByte();
                bbbb = reader.readUShort().getValue();
                setName(insnInfo.simpleMnemonic + " v" + aa + ", v" + bbbb);
                break;
            case _21t: // op vAA, +BBBB
                aa = reader.readUByte();
                bbbb = reader.readShort();
                setName(insnInfo.simpleMnemonic + " v" + aa + ", +" + bbbb);
                break;
            case _21s: // op vAA, #+BBBB
                aa = reader.readUByte();
                bbbb = reader.readShort();
                setName(insnInfo.simpleMnemonic + " v" + aa + ", #+" + bbbb);
                break;
            case _21h: // op vAA, #+BBBB0000
                       // op vAA, #+BBBB000000000000 
                aa = reader.readUByte();
                bbbb = reader.readShort();
                if (opcode == 0x15) {
                    // const/high16 vAA, #+BBBB0000
                    setName(insnInfo.simpleMnemonic + " v" + aa + ", #+" + (bbbb << 16));
                } else {
                    // const-wide/high16 vAA, #+BBBB000000000000
                    setName(insnInfo.simpleMnemonic + " v" + aa + ", #+" + ((long)bbbb << 48));
                }
                break;
            case _21c: // op vAA, type@BBBB
                       // op vAA, field@BBBB
                       // op vAA, string@BBBB 
                aa = reader.readUByte();
                bbbb = reader.readUShort().getValue();
                if (insnInfo.mnemonic.contains("string")) {
                    setName(insnInfo.simpleMnemonic + " v" + aa + ", string@" + bbbb);
                } else if (insnInfo.mnemonic.contains("type")) {
                    setName(insnInfo.simpleMnemonic + " v" + aa + ", type@" + bbbb);
                } else {
                    setName(insnInfo.simpleMnemonic + " v" + aa + ", field@" + bbbb);
                }
                break;
            case _23x: // op vAA, vBB, vCC
                aa = reader.readUByte();
                bb = reader.readUByte();
                cc = reader.readUByte();
                setName(insnInfo.simpleMnemonic + " v" + aa + ", v" + bb + ", v" + cc);
                break;
            case _22b: // op vAA, vBB, #+CC
                aa = reader.readUByte();
                bb = reader.readUByte();
                cc = reader.readByte();
                setName(insnInfo.simpleMnemonic + " v" + aa + ", v" + bb + ", #+" + cc);
                break;
            case _22t: // op vA, vB, +CCCC
                operand = reader.readUByte();
                a = operand & 0b1111;
                b = operand >> 4;
                cccc = reader.readShort();
                setName(insnInfo.simpleMnemonic + " v" + a + ", v" + b + ", +" + cccc);
                break;
            case _22s: // op vA, vB, #+CCCC
                operand = reader.readUByte();
                a = operand & 0b1111;
                b = operand >> 4;
                cccc = reader.readShort();
                setName(insnInfo.simpleMnemonic + " v" + a + ", v" + b + ", #+" + cccc);
                break;
            case _22c: // op vA, vB, type@CCCC
                       // op vA, vB, field@CCCC 
                operand = reader.readUByte();
                a = operand & 0b1111;
                b = operand >> 4;
                cccc = reader.readUShort().getValue();
                if (insnInfo.mnemonic.contains("type")) {
                    setName(insnInfo.simpleMnemonic + " v" + a + ", v" + b + ", type@" + cccc);
                } else {
                    setName(insnInfo.simpleMnemonic + " v" + a + ", v" + b + ", field@" + cccc);
                }
                break;
            case _22cs: // op vA, vB, fieldoff@CCCC
                // todo
                reader.readByte();
                reader.readShort();
                setName(insnInfo.simpleMnemonic);
                break;
            case _30t: // op +AAAAAAAA
                reader.readByte();
                aaaaLo = reader.readShort();
                aaaaHi = reader.readShort();
                setName(insnInfo.simpleMnemonic + "+" + ((aaaaHi << 16) | aaaaLo));
                break;
            case _32x: // op vAAAA, vBBBB
                reader.readByte();
                aaaa = reader.readUShort().getValue();
                bbbb = reader.readUShort().getValue();
                setName(insnInfo.simpleMnemonic + " v" + aaaa + ", v" + bbbb);
                break;
            default:
                throw new FileParseException("XXX");
        }
    }
    
}
