package com.github.zxh.classpy.wasm.instructions;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinPart;
import com.github.zxh.classpy.wasm.WasmBinFile;
import com.github.zxh.classpy.wasm.WasmBinReader;
import com.github.zxh.classpy.wasm.types.BlockType;
import com.github.zxh.classpy.wasm.values.S32;
import com.github.zxh.classpy.wasm.values.S64;
import com.github.zxh.classpy.wasm.values.U32;

public class Instr extends WasmBinPart {

    private int opcode;

    public int getOpcode() {
        return opcode;
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        if ("call".equals(getName())) {
            int funcIdx = Integer.parseInt(getDesc().replace("func#", ""));
            if (funcIdx < wasm.getImportedFuncs().size()) {
                setDesc(wasm.getImportedFuncs().get(funcIdx).getDesc());
            }
        }
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        opcode = readByte(reader, "opcode");

        if (opcode == 0x05) {
            setName("else");
        } else if (opcode == 0x0B) {
            setName("end");
        } else if (opcode < 0x1A) {
            readControlInstructions(reader);
        } else if (opcode < 0x1C) {
            readParametricInstructions(reader);
        } else if (opcode < 0x25) {
            readVariableInstructions(reader);
        } else if (opcode < 0x3F) {
            readMemoryInstructions1(reader);
        } else if (opcode < 0x41) {
            readMemoryInstructions2(reader);
        } else if (opcode < 0x45) {
            readNumericInstructions1(reader);
        } else {
            readNumericInstructions2(reader);
        }

        if (opcode == 0x05        // else
                || opcode == 0x0B // end
                || opcode == 0x1A // drop
                || opcode == 0x1B // select
                || opcode >= 0x45) {
            // no operands
            clear();
        }
    }

    /*
instr ::= 0x00 ⇒ unreachable
        | 0x01 ⇒ nop
        | 0x02 rt:blocktype (in:instr)* 0x0B ⇒ block rt in* end
        | 0x03 rt:blocktype (in:instr)* 0x0B ⇒ loop rt in* end
        | 0x04 rt:blocktype (in:instr)* 0x0B ⇒ if rt in* else 𝜖 end
        | 0x04 rt:blocktype (in1:instr)* 0x05 (in2:instr)* 0x0B ⇒ if rt in* 1 else in* 2 end
        | 0x0C 𝑙:labelidx ⇒ br 𝑙
        | 0x0D 𝑙:labelidx ⇒ br_if 𝑙
        | 0x0E 𝑙*:vec(labelidx) 𝑙𝑁:labelidx ⇒ br_table 𝑙* 𝑙𝑁
        | 0x0F ⇒ return
        | 0x10 𝑥:funcidx ⇒ call 𝑥
        | 0x11 𝑥:typeidx 0x00 ⇒ call_indirect 𝑥
     */
    private void readControlInstructions(WasmBinReader reader) {
        switch (opcode) {
            case 0x00 -> setName("unreachable");
            case 0x01 -> setName("nop");
            case 0x02 -> {
                setName("block");
                readBlock(reader, false);
            }
            case 0x03 -> {
                setName("loop");
                readBlock(reader, false);
            }
            case 0x04 -> {
                setName("if");
                readBlock(reader, true);
            }
            case 0x0C -> {
                setName("br");
                readU32(reader, "label");
            }
            case 0x0D -> {
                setName("br_if");
                readU32(reader, "label");
            }
            case 0x0E -> {
                setName("br_table");
                readVector(reader, "labels", U32::new);
                readU32(reader, "default");
            }
            case 0x0F -> setName("return");
            case 0x10 -> {
                setName("call");
                int idx = readU32(reader, "func");
                setDesc("func#" + idx);
            }
            case 0x11 -> {
                setName("call_indirect");
                readU32(reader, "type");
                readByte(reader, null, (byte) 0x00);
            }
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }
    }

    /*
instr ::= . . .
        | 0x1A ⇒ drop
        | 0x1B ⇒ select
     */
    private void readParametricInstructions(WasmBinReader reader) {
        switch (opcode) {
            case 0x1A -> setName("drop");
            case 0x1B -> setName("select");
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }
    }

    /*
instr ::= . . .
        | 0x20 𝑥:localidx ⇒ local.get 𝑥
        | 0x21 𝑥:localidx ⇒ local.set 𝑥
        | 0x22 𝑥:localidx ⇒ local.tee 𝑥
        | 0x23 𝑥:globalidx ⇒ global.get 𝑥
        | 0x24 𝑥:globalidx ⇒ global.set 𝑥
     */
    private void readVariableInstructions(WasmBinReader reader) {
        switch (opcode) {
            case 0x20 -> setName("local.get");
            case 0x21 -> setName("local.set");
            case 0x22 -> setName("local.tee");
            case 0x23 -> setName("global.get");
            case 0x24 -> setName("global.set");
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }
        int idx = readU32(reader, "index");
        setDesc(Integer.toString(idx));
    }

    /*
memarg ::= 𝑎:u32 𝑜:u32 ⇒ {align 𝑎, offset 𝑜}
instr ::= . . .
        | 0x28 𝑚:memarg ⇒ i32.load 𝑚
        | 0x29 𝑚:memarg ⇒ i64.load 𝑚
        | 0x2A 𝑚:memarg ⇒ f32.load 𝑚
        | 0x2B 𝑚:memarg ⇒ f64.load 𝑚
        | 0x2C 𝑚:memarg ⇒ i32.load8_s 𝑚
        | 0x2D 𝑚:memarg ⇒ i32.load8_u 𝑚
        | 0x2E 𝑚:memarg ⇒ i32.load16_s 𝑚
        | 0x2F 𝑚:memarg ⇒ i32.load16_u 𝑚
        | 0x30 𝑚:memarg ⇒ i64.load8_s 𝑚
        | 0x31 𝑚:memarg ⇒ i64.load8_u 𝑚
        | 0x32 𝑚:memarg ⇒ i64.load16_s 𝑚
        | 0x33 𝑚:memarg ⇒ i64.load16_u 𝑚
        | 0x34 𝑚:memarg ⇒ i64.load32_s 𝑚
        | 0x35 𝑚:memarg ⇒ i64.load32_u 𝑚
        | 0x36 𝑚:memarg ⇒ i32.store 𝑚
        | 0x37 𝑚:memarg ⇒ i64.store 𝑚
        | 0x38 𝑚:memarg ⇒ f32.store 𝑚
        | 0x39 𝑚:memarg ⇒ f64.store 𝑚
        | 0x3A 𝑚:memarg ⇒ i32.store8 𝑚
        | 0x3B 𝑚:memarg ⇒ i32.store16 𝑚
        | 0x3C 𝑚:memarg ⇒ i64.store8 𝑚
        | 0x3D 𝑚:memarg ⇒ i64.store16 𝑚
        | 0x3E 𝑚:memarg ⇒ i64.store32 𝑚
        | 0x3F 0x00 ⇒ memory.size
        | 0x40 0x00 ⇒ memory.grow
     */
    private void readMemoryInstructions1(WasmBinReader reader) {
        switch (opcode) {
            case 0x28 -> setName("i32.load");
            case 0x29 -> setName("i64.load");
            case 0x2A -> setName("f32.load");
            case 0x2B -> setName("f64.load");
            case 0x2C -> setName("i32.load8_s");
            case 0x2D -> setName("i32.load8_u");
            case 0x2E -> setName("i32.load16_s");
            case 0x2F -> setName("i32.load16_u");
            case 0x30 -> setName("i64.load8_s");
            case 0x31 -> setName("i64.load8_u");
            case 0x32 -> setName("i64.load16_s");
            case 0x33 -> setName("i64.load16_u");
            case 0x34 -> setName("i64.load32_s");
            case 0x35 -> setName("i64.load32_u");
            case 0x36 -> setName("i32.store");
            case 0x37 -> setName("i64.store");
            case 0x38 -> setName("f32.store");
            case 0x39 -> setName("f64.store");
            case 0x3A -> setName("i32.store8");
            case 0x3B -> setName("i32.store16");
            case 0x3C -> setName("i64.store8");
            case 0x3D -> setName("i64.store16");
            case 0x3E -> setName("i64.store32");
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }

        readU32(reader, "align");
        readU32(reader, "offset");
    }

    private void readMemoryInstructions2(WasmBinReader reader) {
        switch (opcode) {
            case 0x3F -> setName("memory.size");
            case 0x40 -> setName("memory.grow");
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }

        readByte(reader, null, (byte) 0x00);
    }

    /*
instr ::= . . .
        | 0x41 𝑛:i32 ⇒ i32.const 𝑛
        | 0x42 𝑛:i64 ⇒ i64.const 𝑛
        | 0x43 𝑧:f32 ⇒ f32.const 𝑧
        | 0x44 𝑧:f64 ⇒ f64.const 𝑧
     */
    private void readNumericInstructions1(WasmBinReader reader) {
        switch (opcode) {
            case 0x41 -> {
                setName("i32.const");
                S32 i32 = read(reader, "n", new S32());
                setDesc(i32.getDesc());
            }
            case 0x42 -> {
                setName("i64.const");
                S64 i64 = read(reader, "n", new S64());
                setDesc(i64.getDesc());
            }
            case 0x43 -> {
                setName("i64.const");
                reader.readBytes(4); // todo
            }
            case 0x44 -> {
                setName("i64.const");
                reader.readBytes(8); // todo
            }
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }
    }

    /*
instr ::= . . .
        | 0x45 ⇒ i32.eqz
        | 0x46 ⇒ i32.eq
        | 0x47 ⇒ i32.ne
        | 0x48 ⇒ i32.lt_s
        | 0x49 ⇒ i32.lt_u
        | 0x4A ⇒ i32.gt_s
        | 0x4B ⇒ i32.gt_u
        | 0x4C ⇒ i32.le_s
        | 0x4D ⇒ i32.le_u
        | 0x4E ⇒ i32.ge_s
        | 0x4F ⇒ i32.ge_u

        | 0x50 ⇒ i64.eqz
        | 0x51 ⇒ i64.eq
        | 0x52 ⇒ i64.ne
        | 0x53 ⇒ i64.lt_s
        | 0x54 ⇒ i64.lt_u
        | 0x55 ⇒ i64.gt_s
        | 0x56 ⇒ i64.gt_u
        | 0x57 ⇒ i64.le_s
        | 0x58 ⇒ i64.le_u
        | 0x59 ⇒ i64.ge_s
        | 0x5A ⇒ i64.ge_u

        | 0x5B ⇒ f32.eq
        | 0x5C ⇒ f32.ne
        | 0x5D ⇒ f32.lt
        | 0x5E ⇒ f32.gt
        | 0x5F ⇒ f32.le
        | 0x60 ⇒ f32.ge

        | 0x61 ⇒ f64.eq
        | 0x62 ⇒ f64.ne
        | 0x63 ⇒ f64.lt
        | 0x64 ⇒ f64.gt
        | 0x65 ⇒ f64.le
        | 0x66 ⇒ f64.ge

        | 0x67 ⇒ i32.clz
        | 0x68 ⇒ i32.ctz
        | 0x69 ⇒ i32.popcnt
        | 0x6A ⇒ i32.add
        | 0x6B ⇒ i32.sub
        | 0x6C ⇒ i32.mul
        | 0x6D ⇒ i32.div_s
        | 0x6E ⇒ i32.div_u
        | 0x6F ⇒ i32.rem_s
        | 0x70 ⇒ i32.rem_u
        | 0x71 ⇒ i32.and
        | 0x72 ⇒ i32.or
        | 0x73 ⇒ i32.xor
        | 0x74 ⇒ i32.shl
        | 0x75 ⇒ i32.shr_s
        | 0x76 ⇒ i32.shr_u
        | 0x77 ⇒ i32.rotl
        | 0x78 ⇒ i32.rotr

        | 0x79 ⇒ i64.clz
        | 0x7A ⇒ i64.ctz
        | 0x7B ⇒ i64.popcnt
        | 0x7C ⇒ i64.add
        | 0x7D ⇒ i64.sub
        | 0x7E ⇒ i64.mul
        | 0x7F ⇒ i64.div_s
        | 0x80 ⇒ i64.div_u
        | 0x81 ⇒ i64.rem_s
        | 0x82 ⇒ i64.rem_u
        | 0x83 ⇒ i64.and
        | 0x84 ⇒ i64.or
        | 0x85 ⇒ i64.xor
        | 0x86 ⇒ i64.shl
        | 0x87 ⇒ i64.shr_s
        | 0x88 ⇒ i64.shr_u
        | 0x89 ⇒ i64.rotl
        | 0x8A ⇒ i64.rotr

        | 0x8B ⇒ f32.abs
        | 0x8C ⇒ f32.neg
        | 0x8D ⇒ f32.ceil
        | 0x8E ⇒ f32.floor
        | 0x8F ⇒ f32.trunc
        | 0x90 ⇒ f32.nearest
        | 0x91 ⇒ f32.sqrt
        | 0x92 ⇒ f32.add
        | 0x93 ⇒ f32.sub
        | 0x94 ⇒ f32.mul
        | 0x95 ⇒ f32.div
        | 0x96 ⇒ f32.min
        | 0x97 ⇒ f32.max
        | 0x98 ⇒ f32.copysign

        | 0x99 ⇒ f64.abs
        | 0x9A ⇒ f64.neg
        | 0x9B ⇒ f64.ceil
        | 0x9C ⇒ f64.floor
        | 0x9D ⇒ f64.trunc
        | 0x9E ⇒ f64.nearest
        | 0x9F ⇒ f64.sqrt
        | 0xA0 ⇒ f64.add
        | 0xA1 ⇒ f64.sub
        | 0xA2 ⇒ f64.mul
        | 0xA3 ⇒ f64.div
        | 0xA4 ⇒ f64.min
        | 0xA5 ⇒ f64.max
        | 0xA6 ⇒ f64.copysign

        | 0xA7 ⇒ i32.wrap_i64
        | 0xA8 ⇒ i32.trunc_f32_s
        | 0xA9 ⇒ i32.trunc_f32_u
        | 0xAA ⇒ i32.trunc_f64_s
        | 0xAB ⇒ i32.trunc_f64_u
        | 0xAC ⇒ i64.extend_i32_s
        | 0xAD ⇒ i64.extend_i32_u
        | 0xAE ⇒ i64.trunc_f32_s
        | 0xAF ⇒ i64.trunc_f32_u
        | 0xB0 ⇒ i64.trunc_f64_s
        | 0xB1 ⇒ i64.trunc_f64_u
        | 0xB2 ⇒ f32.convert_i32_s
        | 0xB3 ⇒ f32.convert_i32_u
        | 0xB4 ⇒ f32.convert_i64_s
        | 0xB5 ⇒ f32.convert_i64_u
        | 0xB6 ⇒ f32.demote_f64
        | 0xB7 ⇒ f64.convert_i32_s
        | 0xB8 ⇒ f64.convert_i32_u
        | 0xB9 ⇒ f64.convert_i64_s
        | 0xBA ⇒ f64.convert_i64_u
        | 0xBB ⇒ f64.promote_f32
        | 0xBC ⇒ i32.reinterpret_f32
        | 0xBD ⇒ i64.reinterpret_f64
        | 0xBE ⇒ f32.reinterpret_i32
        | 0xBF ⇒ f64.reinterpret_i64
     */
    private void readNumericInstructions2(WasmBinReader reader) {
        switch (opcode) {
            case 0x45 -> setName("i32.eqz");
            case 0x46 -> setName("i32.eq");
            case 0x47 -> setName("i32.ne");
            case 0x48 -> setName("i32.lt_s");
            case 0x49 -> setName("i32.lt_u");
            case 0x4A -> setName("i32.gt_s");
            case 0x4B -> setName("i32.gt_u");
            case 0x4C -> setName("i32.le_s");
            case 0x4D -> setName("i32.le_u");
            case 0x4E -> setName("i32.ge_s");
            case 0x4F -> setName("i32.ge_u");
            case 0x50 -> setName("i64.eqz");
            case 0x51 -> setName("i64.eq");
            case 0x52 -> setName("i64.ne");
            case 0x53 -> setName("i64.lt_s");
            case 0x54 -> setName("i64.lt_u");
            case 0x55 -> setName("i64.gt_s");
            case 0x56 -> setName("i64.gt_u");
            case 0x57 -> setName("i64.le_s");
            case 0x58 -> setName("i64.le_u");
            case 0x59 -> setName("i64.ge_s");
            case 0x5A -> setName("i64.ge_u");
            case 0x5B -> setName("f32.eq");
            case 0x5C -> setName("f32.ne");
            case 0x5D -> setName("f32.lt");
            case 0x5E -> setName("f32.gt");
            case 0x5F -> setName("f32.le");
            case 0x60 -> setName("f32.ge");
            case 0x61 -> setName("f64.eq");
            case 0x62 -> setName("f64.ne");
            case 0x63 -> setName("f64.lt");
            case 0x64 -> setName("f64.gt");
            case 0x65 -> setName("f64.le");
            case 0x66 -> setName("f64.ge");
            case 0x67 -> setName("i32.clz");
            case 0x68 -> setName("i32.ctz");
            case 0x69 -> setName("i32.popcnt");
            case 0x6A -> setName("i32.add");
            case 0x6B -> setName("i32.sub");
            case 0x6C -> setName("i32.mul");
            case 0x6D -> setName("i32.div_s");
            case 0x6E -> setName("i32.div_u");
            case 0x6F -> setName("i32.rem_s");
            case 0x70 -> setName("i32.rem_u");
            case 0x71 -> setName("i32.and");
            case 0x72 -> setName("i32.or");
            case 0x73 -> setName("i32.xor");
            case 0x74 -> setName("i32.shl");
            case 0x75 -> setName("i32.shr_s");
            case 0x76 -> setName("i32.shr_u");
            case 0x77 -> setName("i32.rotl");
            case 0x78 -> setName("i32.rotr");
            case 0x79 -> setName("i64.clz");
            case 0x7A -> setName("i64.ctz");
            case 0x7B -> setName("i64.popcnt");
            case 0x7C -> setName("i64.add");
            case 0x7D -> setName("i64.sub");
            case 0x7E -> setName("i64.mul");
            case 0x7F -> setName("i64.div_s");
            case 0x80 -> setName("i64.div_u");
            case 0x81 -> setName("i64.rem_s");
            case 0x82 -> setName("i64.rem_u");
            case 0x83 -> setName("i64.and");
            case 0x84 -> setName("i64.or");
            case 0x85 -> setName("i64.xor");
            case 0x86 -> setName("i64.shl");
            case 0x87 -> setName("i64.shr_s");
            case 0x88 -> setName("i64.shr_u");
            case 0x89 -> setName("i64.rotl");
            case 0x8A -> setName("i64.rotr");
            case 0x8B -> setName("f32.abs");
            case 0x8C -> setName("f32.neg");
            case 0x8D -> setName("f32.ceil");
            case 0x8E -> setName("f32.floor");
            case 0x8F -> setName("f32.trunc");
            case 0x90 -> setName("f32.nearest");
            case 0x91 -> setName("f32.sqrt");
            case 0x92 -> setName("f32.add");
            case 0x93 -> setName("f32.sub");
            case 0x94 -> setName("f32.mul");
            case 0x95 -> setName("f32.div");
            case 0x96 -> setName("f32.min");
            case 0x97 -> setName("f32.max");
            case 0x98 -> setName("f32.copysign");
            case 0x99 -> setName("f64.abs");
            case 0x9A -> setName("f64.neg");
            case 0x9B -> setName("f64.ceil");
            case 0x9C -> setName("f64.floor");
            case 0x9D -> setName("f64.trunc");
            case 0x9E -> setName("f64.nearest");
            case 0x9F -> setName("f64.sqrt");
            case 0xA0 -> setName("f64.add");
            case 0xA1 -> setName("f64.sub");
            case 0xA2 -> setName("f64.mul");
            case 0xA3 -> setName("f64.div");
            case 0xA4 -> setName("f64.min");
            case 0xA5 -> setName("f64.max");
            case 0xA6 -> setName("f64.copysign");
            case 0xA7 -> setName("i32.wrap_i64");
            case 0xA8 -> setName("i32.trunc_f32_s");
            case 0xA9 -> setName("i32.trunc_f32_u");
            case 0xAA -> setName("i32.trunc_f64_s");
            case 0xAB -> setName("i32.trunc_f64_u");
            case 0xAC -> setName("i64.extend_i32_s");
            case 0xAD -> setName("i64.extend_i32_u");
            case 0xAE -> setName("i64.trunc_f32_s");
            case 0xAF -> setName("i64.trunc_f32_u");
            case 0xB0 -> setName("i64.trunc_f64_s");
            case 0xB1 -> setName("i64.trunc_f64_u");
            case 0xB2 -> setName("f32.convert_i32_s");
            case 0xB3 -> setName("f32.convert_i32_u");
            case 0xB4 -> setName("f32.convert_i64_s");
            case 0xB5 -> setName("f32.convert_i64_u");
            case 0xB6 -> setName("f32.demote_f64");
            case 0xB7 -> setName("f64.convert_i32_s");
            case 0xB8 -> setName("f64.convert_i32_u");
            case 0xB9 -> setName("f64.convert_i64_s");
            case 0xBA -> setName("f64.convert_i64_u");
            case 0xBB -> setName("f64.promote_f32");
            case 0xBC -> setName("i32.reinterpret_f32");
            case 0xBD -> setName("i64.reinterpret_f64");
            case 0xBE -> setName("f32.reinterpret_i32");
            case 0xBF -> setName("f64.reinterpret_i64");
            default -> throw new ParseException(String.format(
                    "Invalid opcode: 0x%02X", opcode));
        }
    }

    private void readBlock(WasmBinReader reader, boolean isIfBlock) {
        read(reader, "rt", new BlockType());

        // instrs
        if (isIfBlock) {
            while (reader.remaining() > 0) {
                Instr instr = read(reader, null, new Instr());
                if (instr.opcode == 0x05) { // else
                    break;
                }
            }
        }
        while (reader.remaining() > 0) {
            Instr instr = read(reader, null, new Instr());
            if (instr.opcode == 0x0B) { // end
                break;
            }
        }
    }

}
