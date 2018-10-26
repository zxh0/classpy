package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinFile;
import com.github.zxh.classpy.wasm.WasmBinReader;
import com.github.zxh.classpy.wasm.types.FuncType;
import com.github.zxh.classpy.wasm.types.Limits;
import com.github.zxh.classpy.wasm.types.TableType;
import com.github.zxh.classpy.wasm.values.Byte;
import com.github.zxh.classpy.wasm.values.Index;
import com.github.zxh.classpy.wasm.values.Name;

import java.util.List;
import java.util.stream.Collectors;

public class Section extends WasmBinComponent {

    private int id;

    public int getID() {
        return id;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        id = readID(reader);
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
            readCustomSection(reader, size);
        } else if (id == 1) {
            setName("type section");
            readVector(reader, "types", FuncType::new);
        } else if (id == 2) {
            setName("import section");
            readVector(reader, "imports", Import::new);
        } else if (id == 3) {
            setName("function section");
            readVector(reader, "functions", Index::new);
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
            readIndex(reader, "funcidx");
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

    private void readCustomSection(WasmBinReader reader, int size) {
        setName("custom section");
        int pos1 = reader.getPosition();
        read(reader, "name", new Name());
        int pos2 = reader.getPosition();
        size -= (pos2 - pos1);
        if (size > 0) {
            readBytes(reader, "contents", size);
        }
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        if (id == 10) { // code section
            List<Code> codes = getComponents().get(2)
                    .getComponents().stream().skip(1)
                    .map(c -> (Code) c)
                    .collect(Collectors.toList());

            int importedFuncCount = wasm.getImportedFuncs().size();
//            for (int i = 0; i < codes.size(); i++) {
//                codes.get(i).setDesc("func#" + (importedFuncCount + i));
//            }

            for (Export export : wasm.getExportedFuncs()) {
                int idx = export.getFuncIdx() - importedFuncCount;
                if (idx < codes.size()) {
                    codes.get(idx).setDesc(export.getDesc());
                }
            }
        }
    }

}
