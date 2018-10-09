package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;
import com.github.zxh.classpy.wasm.types.FuncType;
import com.github.zxh.classpy.wasm.types.Limits;
import com.github.zxh.classpy.wasm.types.TableType;
import com.github.zxh.classpy.wasm.values.Byte;
import com.github.zxh.classpy.wasm.values.U32;

public class Section extends WasmBinComponent {

    protected void readContent(WasmBinReader reader) {
        int id = readID(reader);
        int size = readU32(reader, "size");
        readContents(reader, id, size);
    }

    private int readID(WasmBinReader reader) {
        Byte id = new Byte();
        add("id", id);
        id.read(reader);
        id.setDesc(Integer.toString(id.getValue()));
        return id.getValue();
    }

    private void readContents(WasmBinReader reader,
                              int id, int size) {
        if (id == 0) {
            setName("custom section");
            readBytes(reader, "contents", size);
        } else if (id == 1) {
            setName("type section");
            readVector(reader, "types", FuncType::new);
        } else if (id == 2) {
            setName("import section");
            readVector(reader, "imports", Import::new);
        } else if (id == 3) {
            setName("function section");
            readVector(reader, "functions", U32::new);
        } else if (id == 4) {
            setName("table section");
            readVector(reader, "tables", TableType::new);
        } else if (id == 5) {
            setName("memory section");
            readVector(reader, "memories", Limits::new);
        } else if (id == 6) {
            setName("global section");
            readVector(reader, "globals", Global::new);
        } else if (id == 7) {
            setName("export section");
            readVector(reader, "exports", Export::new);
        } else if (id == 8) {
            setName("start section");
            reader.readU32();
        } else if (id == 9) {
            setName("element section");
            readVector(reader, "elements", Element::new);
        } else if (id == 10) {
            setName("code section");
            readVector(reader, "codes", Code::new);
        } else if (id == 11) {
            setName("data section");
            readVector(reader, "datas", Data::new);
        } else {
            throw new ParseException("Invalid section id: " + id);
        }
    }

}
