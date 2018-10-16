package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.bitcoin.types.Bytes;
import com.github.zxh.classpy.common.ParseException;

// https://en.bitcoin.it/wiki/Script
public class Script extends BlockComponent {

    @Override
    protected void readContent(BlockReader reader) {
        long n = readVarInt(reader, "Length");
        int basePos = reader.getPosition();
        Bytes bytes = new Bytes((int) n);
        bytes.read(reader);
        decodeScript(bytes.getBytes(), basePos);
    }

    private void decodeScript(byte[] code, int basePos) {
        try {
            decodeScript(new BlockReader(code) {
                @Override
                public int getPosition() {
                    return basePos + super.getPosition();
                }
            });
        } catch (Exception e) {
            System.err.println(bytes2Hex(code));
            e.printStackTrace(System.err);
        }
    }

    private static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toHexString(bytes[i] & 0xFF));
        }
        return sb.toString();
    }

    private void decodeScript(BlockReader reader) {
        while (reader.remaining() > 0) {
            Instr instr = new Instr();
            add("Instr", instr);
            instr.read(reader);
        }
    }


    private static class Instr extends BlockComponent {

        @Override
        protected void readContent(BlockReader reader) {
            int opcode = reader.readByte() & 0xFF;
            if (opcode == 0) {
                setName("OP_0"); // OP_FALSE
            } else if (opcode <= 0x4B) {
                setName("OP_PUSH<" + opcode + ">");
                reader.readBytes(opcode);
            } else if (opcode == 0x4C) {
                setName("OP_PUSHDATA1");
                int n = reader.readUnsignedByte();
                reader.readBytes(n);
            } else if (opcode == 0x4D) {
                setName("OP_PUSHDATA2");
                int n = reader.readUnsignedShort();
                reader.readBytes(n);
            } else if (opcode == 0x4E) {
                setName("OP_PUSHDATA4");
                int n = (int) reader.readUnsignedInt();
                reader.readBytes(n);
            } else {
                setOpcodeName(opcode);
            }
        }

        private void setOpcodeName(int opcode) {
            switch (opcode) {
                // 0x4F ~ 0x60 Constants
                case 0x4F: setName("OP_1NEGATE"); break;
                case 0x51: setName("OP_1"); break; // OP_TRUE
                case 0x52: setName("OP_2"); break;
                case 0x53: setName("OP_3"); break;
                case 0x54: setName("OP_4"); break;
                case 0x55: setName("OP_5"); break;
                case 0x56: setName("OP_6"); break;
                case 0x57: setName("OP_7"); break;
                case 0x58: setName("OP_8"); break;
                case 0x59: setName("OP_9"); break;
                case 0x5A: setName("OP_10"); break;
                case 0x5B: setName("OP_11"); break;
                case 0x5C: setName("OP_12"); break;
                case 0x5D: setName("OP_13"); break;
                case 0x5E: setName("OP_14"); break;
                case 0x5F: setName("OP_15"); break;
                case 0x60: setName("OP_16"); break;
                // 0x61 ~ 0x6A Flow control
                case 0x61: setName("OP_NOP"); break;
                case 0x63: setName("OP_IF"); break;
                case 0x64: setName("OP_NOTIF"); break;
                case 0x66: setName("OP_ELSE"); break;
                case 0x68: setName("OP_ENDIF"); break;
                case 0x69: setName("OP_VERIFY"); break;
                case 0x6A: setName("OP_RETURN"); break;
                // 0x6B ~ 0x7D Stack
                case 0x6b: setName("OP_TOALTSTACK"); break;
                case 0x6c: setName("OP_FROMALTSTACK"); break;
                case 0x73: setName("OP_IFDUP"); break;
                case 0x74: setName("OP_DEPTH"); break;
                case 0x75: setName("OP_DROP"); break;
                case 0x76: setName("OP_DUP"); break;
                case 0x77: setName("OP_NIP"); break;
                case 0x78: setName("OP_OVER"); break;
                case 0x79: setName("OP_PICK"); break;
                case 0x7a: setName("OP_ROLL"); break;
                case 0x7b: setName("OP_ROT"); break;
                case 0x7c: setName("OP_SWAP"); break;
                case 0x7d: setName("OP_TUCK"); break;
                case 0x6d: setName("OP_2DROP"); break;
                case 0x6e: setName("OP_2DUP"); break;
                case 0x6f: setName("OP_3DUP"); break;
                case 0x70: setName("OP_2OVER"); break;
                case 0x71: setName("OP_2ROT"); break;
                case 0x72: setName("OP_2SWAP"); break;
                // 0x7E ~ 0x82 Splice
                case 0x7E: setName("OP_CAT!"); break;
                case 0x7F: setName("OP_SUBSTR!"); break;
                case 0x80: setName("OP_LEFT!"); break;
                case 0x81: setName("OP_RIGHT!"); break;
                case 0x82: setName("OP_SIZE"); break;
                // 0x83 ~ 0x88 Bitwise logic
                case 0x83: setName("OP_INVERT!"); break;
                case 0x84: setName("OP_AND!"); break;
                case 0x85: setName("OP_OR!"); break;
                case 0x86: setName("OP_XOR!"); break;
                case 0x87: setName("OP_EQUAL"); break;
                case 0x88: setName("OP_EQUALVERIFY"); break;
                // 0xA6 ~ 0xAF Crypto
                case 0xA6: setName("OP_RIPEMD160"); break;
                case 0xA7: setName("OP_SHA1"); break;
                case 0xA8: setName("OP_SHA256"); break;
                case 0xA9: setName("OP_HASH160"); break;
                case 0xAA: setName("OP_HASH256"); break;
                case 0xAB: setName("OP_CODESEPARATOR"); break;
                case 0xAC: setName("OP_CHECKSIG"); break;
                case 0xAD: setName("OP_CHECKSIGVERIFY"); break;
                case 0xAE: setName("OP_CHECKMULTISIG"); break;
                case 0xAF: setName("OP_CHECKMULTISIGVERIFY"); break;
                default: throw new ParseException(
                        String.format("Invalid opcode: 0x%02X", opcode));
            }
        }
    }

}
