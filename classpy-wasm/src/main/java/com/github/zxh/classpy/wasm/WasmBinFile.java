package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.wasm.sections.Export;
import com.github.zxh.classpy.wasm.sections.Section;

import java.util.List;
import java.util.stream.Collectors;

public class WasmBinFile extends WasmBinComponent {

    private List<String> importedFuncs;
    private List<Export> exportedFuncs;

    public List<String> getImportedFuncs() {
        return importedFuncs;
    }

    public List<Export> getExportedFuncs() {
        return exportedFuncs;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        readBytes(reader, "magic", 4);
        readBytes(reader, "version", 4);
        readSections(reader);
        findImportedFuncs();
        findExportedFuncs();
    }

    private void readSections(WasmBinReader reader) {
        while (reader.remaining() > 0) {
            Section section = new Section();
            add("section", section);
            section.read(reader);
        }
    }

    private void findImportedFuncs() {
         importedFuncs = getComponents().stream()
                .filter(c -> c instanceof Section)                // section?
                .map(c -> (Section) c)                            // yes
                .filter(sec -> sec.getID() == 2)                  // imports?
                .map(sec -> (Vector) sec.getComponents().get(2))  // vector
                .flatMap(v -> v.getComponents().stream().skip(1)) // imports
                .map(FileComponent::getDesc)                      // description
                .filter(d -> d.endsWith("()"))                    // function
                .collect(Collectors.toList());
    }

    private void findExportedFuncs() {
        exportedFuncs = getComponents().stream()
                .filter(c -> c instanceof Section)                // section?
                .map(c -> (Section) c)                            // yes
                .filter(sec -> sec.getID() == 7)                  // exports?
                .map(sec -> (Vector) sec.getComponents().get(2))  // vector
                .flatMap(v -> v.getComponents().stream().skip(1)) // exports
                .map(c -> (Export) c)                             // exports
                .filter(x -> x.getFuncIdx() >= 0)                 // function
                .collect(Collectors.toList());
    }

}
