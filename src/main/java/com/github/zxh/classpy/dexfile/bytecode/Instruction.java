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
                reader.readByte();
                setName(insnInfo.mnemonic);
                break;
            default:
                throw new FileParseException("XXX");
        }
    }
    
}
