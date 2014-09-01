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
        InstructionInfo insnInfo = InstructionSet.getInstructionInfo(opcode);
        switch (insnInfo.format) {
            case _00x:
                reader.readByte();
                break;
            case _10x:
            case _12x:
            case _11n:
            case _11x:
            case _10t:
                reader.readByte();
                setName(insnInfo.mnemonic);
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
                setName(insnInfo.mnemonic);
                break;
            default:
                throw new FileParseException("XXX");
        }
    }
    
}
