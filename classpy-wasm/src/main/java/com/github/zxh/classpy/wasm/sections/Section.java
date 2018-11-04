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

import java.util.List;

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
        int pos1 = reader.getPosition();
        String name = readName(reader, "name");
        setName("custom section: " + name);

        if (name.equals("name")) {
            readNameData(reader);
        } else if (name.equals("dylink")) {
            readDyLinkData(reader);
        } else {
            int pos2 = reader.getPosition();
            size -= (pos2 - pos1);
            if (size > 0) {
                readBytes(reader, "contents", size);
            }
        }
    }

    private void readNameData(WasmBinReader reader) {
        while (reader.remaining() > 0) {
            int subID = readByte(reader, "subID");
            int size = readU32(reader, "size");
            if (subID == 1) {
                readVector(reader, "function names", NameAssoc::new);
            } else {
                readBytes(reader, "contents", size);
            }
        }
    }

    private void readDyLinkData(WasmBinReader reader) {
        readU32(reader, "memorySize");
        readU32(reader, "memoryAlignment");
        readU32(reader, "tableSize");
        readU32(reader, "tableAlignment");
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        if (id == 1) {
            postReadTypes(wasm);
        } else if (id == 2) {
            postReadImports(wasm);
        } else if (id == 10) {
            postReadCodes(wasm);
        }
    }

    private void postReadTypes(WasmBinFile wasm) {
        int typeIdx = 0;
        for (FuncType ft : wasm.getFuncTypes()) {
            ft.setName("#" + (typeIdx++));
        }
    }

    private void postReadImports(WasmBinFile wasm) {
        int funcIdx = 0;
        int globalIdx = 0;
        for (Import imp : wasm.getImports()) {
            if (imp.isFunc()) {
                imp.setName("func#" + (funcIdx++));
            } else if (imp.isGlobal()) {
                imp.setName("global#" + (globalIdx++));
            }
        }
        for (Index idx : wasm.getFuncs()) {
            idx.setName("func#" + (funcIdx++));
            idx.setDesc("type" + idx.getDesc());
        }
        for (Global glb : wasm.getGlobals()) {
            glb.setName("global#" + (globalIdx++));
        }
    }

    private void postReadCodes(WasmBinFile wasm) {
        List<Code> codes = wasm.getCodes();
        int importedFuncCount = wasm.getImportedFuncs().size();
        for (int i = 0; i < codes.size(); i++) {
            codes.get(i).setName("func#" + (importedFuncCount + i));
        }
        for (Export export : wasm.getExports()) {
            if (export.getFuncIdx() >= 0) {
                int idx = export.getFuncIdx() - importedFuncCount;
                if (idx < codes.size()) {
                    codes.get(idx).setDesc(export.getDesc());
                }
            }
        }
    }

    private static class NameAssoc extends WasmBinComponent {

        @Override
        protected void readContent(WasmBinReader reader) {
            int idx = readIndex(reader, "idx");
            String name = readName(reader, "name");
            setName("#" + idx);
            setDesc(name + "()");
        }

    }

}
