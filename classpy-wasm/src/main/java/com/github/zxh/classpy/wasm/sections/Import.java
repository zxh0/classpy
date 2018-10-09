package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;
import com.github.zxh.classpy.wasm.types.GlobalType;
import com.github.zxh.classpy.wasm.types.Limits;
import com.github.zxh.classpy.wasm.types.TableType;
import com.github.zxh.classpy.wasm.values.U32;

public class Import extends WasmBinComponent {

    {
        name("module");
        name("name");
        add("desc", new Desc());
    }

    @Override
    protected void postRead() {
        setDesc(get("module").getDesc() + "." + get("name").getDesc());
    }


    private static class Desc extends WasmBinComponent {

        @Override
        protected void readContent(WasmBinReader reader) {
            int b = readByte(reader, null);
            switch (b) {
                case 0x00: read(reader, "func",   U32::new);        break; // typeidx
                case 0x01: read(reader, "table",  TableType::new);  break;
                case 0x02: read(reader, "mem",    Limits::new);     break;
                case 0x03: read(reader, "global", GlobalType::new); break;
                default: throw new ParseException("Invalid import desc: " + b);
            }
        }

    }

}
