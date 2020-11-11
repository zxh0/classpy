package com.github.zxh.classpy.ethereum;

import com.github.zxh.classpy.ethereum.evm.Opcode;

public class Instruction extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        int opc = reader.readByte() & 0xFF;
        Opcode opcode = Opcode.valueOf2(opc);
        if (opcode == null) { // Unknown opcode
            setName(EvmHelper.encodeHexStr(new byte[] {(byte) opc}));
        } else {
            setName(opcode.name());
            byte[] operands = reader.readBytes(opcode.n);
            if (operands.length > 0) {
                setDesc(EvmHelper.encodeHexStr(operands));
            }
        }
    }

}
