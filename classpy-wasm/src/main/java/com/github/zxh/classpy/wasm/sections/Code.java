package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

import java.util.Base64;

public class Code extends WasmBinComponent {

//    {
//        u32("size");
//        add("code", new Func());
//    }

    @Override
    protected void readContent(WasmBinReader reader) {
        setName("code");
        int size = readU32(reader, "size");

        int pos = reader.getPosition();
        byte[] code = reader.readBytes(size);
        Func func = new Func();
        add("func", func);

        try {
            func.read(new WasmBinReader(code) {
                @Override
                public int getPosition() {
                    return pos + super.getPosition();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Base64.getEncoder().encodeToString(code));
        }
    }


    public static class Func extends WasmBinComponent {

        {
            vector("locals", Locals::new);
            expr("expr");
        }

    }


    private static class Locals extends WasmBinComponent {

        {
            u32("n");
            valType("type");
        }

        @Override
        protected void postRead() {
            setDesc(get("type").getDesc() + " x " + get("n").getDesc());
        }

    }

}
