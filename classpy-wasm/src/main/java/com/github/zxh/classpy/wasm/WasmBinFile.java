package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.wasm.sections.*;
import com.github.zxh.classpy.wasm.types.FuncType;
import com.github.zxh.classpy.wasm.values.Index;

import java.util.List;
import java.util.stream.Collectors;

public class WasmBinFile extends WasmBinComponent {

    private List<FuncType> funcTypes;
    private List<Import> imports;
    private List<Import> importedFuncs;
    private List<Index> funcs;
    private List<Global> globals;
    private List<Export> exports;
    private List<Code> codes;

    public List<FuncType> getFuncTypes() { return funcTypes; }
    public List<Import> getImports() { return imports; }
    public List<Import> getImportedFuncs() { return importedFuncs; }
    public List<Index> getFuncs() { return funcs; }
    public List<Global> getGlobals() { return globals; }
    public List<Export> getExports() { return exports; }
    public List<Code> getCodes() { return codes; }

    @Override
    protected void readContent(WasmBinReader reader) {
        readBytes(reader, "magic", 4);
        readBytes(reader, "version", 4);
        readSections(reader);
        funcTypes = getSectionItems(1, FuncType.class);
        imports = getSectionItems(2, Import.class);
        importedFuncs = imports.stream()
                .filter(Import::isFunc)
                .collect(Collectors.toList());
        funcs = getSectionItems(3, Index.class);
        globals = getSectionItems(6, Global.class);
        exports = getSectionItems(7, Export.class);
        codes = getSectionItems(10, Code.class);
    }

    private void readSections(WasmBinReader reader) {
        while (reader.remaining() > 0) {
            Section section = new Section();
            add("section", section);
            section.read(reader);
        }
    }

    private <T> List<T> getSectionItems(int secID, Class<T> itemClass) {
        return getComponents().stream()
                .filter(c -> c instanceof Section)                // section?
                .map(c -> (Section) c)                            // yes
                .filter(sec -> sec.getID() == secID)              // section
                .map(sec -> (Vector) sec.getComponents().get(2))  // vector
                .flatMap(v -> v.getComponents().stream().skip(1)) // items
                .map(c -> itemClass.cast(c))                      // Ts
                .collect(Collectors.toList());
    }

}
