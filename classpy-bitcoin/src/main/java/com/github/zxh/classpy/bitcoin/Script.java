package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.bitcoin.types.Bytes;
import com.github.zxh.classpy.common.ParseException;

// https://en.bitcoin.it/wiki/Script
public class Script extends BlockPart {

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
            add("?", instr);
            instr.read(reader);
        }
    }


    private static class Instr extends BlockPart {

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
                case 0x4F -> setName("OP_1NEGATE");
                case 0x51 -> setName("OP_1"); // OP_TRUE
                case 0x52 -> setName("OP_2");
                case 0x53 -> setName("OP_3");
                case 0x54 -> setName("OP_4");
                case 0x55 -> setName("OP_5");
                case 0x56 -> setName("OP_6");
                case 0x57 -> setName("OP_7");
                case 0x58 -> setName("OP_8");
                case 0x59 -> setName("OP_9");
                case 0x5A -> setName("OP_10");
                case 0x5B -> setName("OP_11");
                case 0x5C -> setName("OP_12");
                case 0x5D -> setName("OP_13");
                case 0x5E -> setName("OP_14");
                case 0x5F -> setName("OP_15");
                case 0x60 -> setName("OP_16");
                // 0x61 ~ 0x6A Flow control
                case 0x61 -> setName("OP_NOP");
                case 0x63 -> setName("OP_IF");
                case 0x64 -> setName("OP_NOTIF");
                case 0x66 -> setName("OP_ELSE");
                case 0x68 -> setName("OP_ENDIF");
                case 0x69 -> setName("OP_VERIFY");
                case 0x6A -> setName("OP_RETURN");
                // 0x6B ~ 0x7D Stack
                case 0x6b -> setName("OP_TOALTSTACK");
                case 0x6c -> setName("OP_FROMALTSTACK");
                case 0x73 -> setName("OP_IFDUP");
                case 0x74 -> setName("OP_DEPTH");
                case 0x75 -> setName("OP_DROP");
                case 0x76 -> setName("OP_DUP");
                case 0x77 -> setName("OP_NIP");
                case 0x78 -> setName("OP_OVER");
                case 0x79 -> setName("OP_PICK");
                case 0x7a -> setName("OP_ROLL");
                case 0x7b -> setName("OP_ROT");
                case 0x7c -> setName("OP_SWAP");
                case 0x7d -> setName("OP_TUCK");
                case 0x6d -> setName("OP_2DROP");
                case 0x6e -> setName("OP_2DUP");
                case 0x6f -> setName("OP_3DUP");
                case 0x70 -> setName("OP_2OVER");
                case 0x71 -> setName("OP_2ROT");
                case 0x72 -> setName("OP_2SWAP");
                // 0x7E ~ 0x82 Splice
                case 0x7E -> setName("OP_CAT!");
                case 0x7F -> setName("OP_SUBSTR!");
                case 0x80 -> setName("OP_LEFT!");
                case 0x81 -> setName("OP_RIGHT!");
                case 0x82 -> setName("OP_SIZE");
                // 0x83 ~ 0x88 Bitwise logic
                case 0x83 -> setName("OP_INVERT!");
                case 0x84 -> setName("OP_AND!");
                case 0x85 -> setName("OP_OR!");
                case 0x86 -> setName("OP_XOR!");
                case 0x87 -> setName("OP_EQUAL");
                case 0x88 -> setName("OP_EQUALVERIFY");
                // 0xA6 ~ 0xAF Crypto
                case 0xA6 -> setName("OP_RIPEMD160");
                case 0xA7 -> setName("OP_SHA1");
                case 0xA8 -> setName("OP_SHA256");
                case 0xA9 -> setName("OP_HASH160");
                case 0xAA -> setName("OP_HASH256");
                case 0xAB -> setName("OP_CODESEPARATOR");
                case 0xAC -> setName("OP_CHECKSIG");
                case 0xAD -> setName("OP_CHECKSIGVERIFY");
                case 0xAE -> setName("OP_CHECKMULTISIG");
                case 0xAF -> setName("OP_CHECKMULTISIGVERIFY");
                default -> throw new ParseException(
                        String.format("Invalid opcode -> 0x%02X", opcode));
            }
        }
    }

}
