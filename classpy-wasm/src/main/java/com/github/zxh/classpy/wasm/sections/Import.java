package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;
import com.github.zxh.classpy.wasm.types.GlobalType;
import com.github.zxh.classpy.wasm.types.Limits;
import com.github.zxh.classpy.wasm.types.TableType;
import com.github.zxh.classpy.wasm.values.Index;
import com.github.zxh.classpy.wasm.values.Name;

public class Import extends WasmBinComponent {

    @Override
    protected void readContent(WasmBinReader reader) {
        Name module = read(reader, "module", new Name());
        Name name = read(reader, "name", new Name());
        Desc desc = read(reader, "desc", new Desc());
        setDesc(module.getDesc() + "." + name.getDesc());
        if (desc.b == 0) { // func
            setDesc(getDesc() + "()");
        }
    }


    private static class Desc extends WasmBinComponent {

        private int b;

        @Override
        protected void readContent(WasmBinReader reader) {
            b = readByte(reader, null);
            switch (b) {
                case 0x00: read(reader, "func",   new Index());      break;
                case 0x01: read(reader, "table",  new TableType());  break;
                case 0x02: read(reader, "mem",    new Limits());     break;
                case 0x03: read(reader, "global", new GlobalType()); break;
                default: throw new ParseException("Invalid import desc: " + b);
            }
        }

    }

}
