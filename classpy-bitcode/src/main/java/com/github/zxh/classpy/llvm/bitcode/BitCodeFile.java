package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.llvm.bitcode.types.U32Hex;

// https://llvm.org/docs/BitCodeFormat.html
public class BitCodeFile extends BitCodePart {

    @Override
    protected void readContent(BitCodeReader reader) {
        Wrapper wrapper = new Wrapper();
        wrapper.read(reader);
        add("Wrapper", wrapper);

        U32Hex magic = new U32Hex();
        magic.read(reader);
        add("LLVM IR Magic", magic);

        while (reader.remaining() > 0) {
            Block block = new Block(2);
            add("Block", block);
            block.read(reader);
        }
    }

}
