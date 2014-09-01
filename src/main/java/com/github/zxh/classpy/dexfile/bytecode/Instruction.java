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
        int vA, vB;
        
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
                vA = operand & 0b1111;
                vB = operand >> 4;
                setName(insnInfo.simpleMnemonic + " v" + vA + ", v" + vB);
                break;
            case _11n: // const/4 vA, #+B
                operand = reader.readUByte();
                vA = operand & 0b1111;
                vB = operand >> 4;
                setName(insnInfo.simpleMnemonic + " v" + vA + ", #+" + vB);
                break;
            case _11x: // op vAA
                operand = reader.readUByte();
                setName(insnInfo.simpleMnemonic + " v" + operand);
                break;
            case _10t: // op +AA
                operand = reader.readUByte();
                setName(insnInfo.simpleMnemonic + " +" + operand);
                break;
            case _20t:
            case _20bc:
            case _22x:
            case _21t:
            case _21s:
            case _21h:
            case _21c:
            case _23x:
            case _22b:
            case _22t:
            case _22s:
            case _22c:
            case _22cs:
                reader.readByte();
                reader.readUShort();
                setName(insnInfo.simpleMnemonic);
                break;
            default:
                throw new FileParseException("XXX");
        }
    }
    
}
