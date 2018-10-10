package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.wasm.sections.Section;

public class WasmBinFile extends WasmBinComponent {

    @Override
    protected void readContent(WasmBinReader reader) {
        readBytes(reader, "magic", 4);
        readBytes(reader, "version", 4);
        readSections(reader);
    }

    private void readSections(WasmBinReader reader) {
        while (reader.remaining() > 0) {
            Section section = new Section();
            add("section", section);
            section.read(reader);
        }
    }

}
