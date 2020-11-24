package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.common.FileParser;

public class BitCodeParser implements FileParser {

    @Override
    public BitCodeFile parse(byte[] data) {
        BitCodeFile bcf = new BitCodeFile();
        try {
            bcf.read(new BitCodeReader(data));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return bcf;
    }

    private static void postRead(BitCodePart part,
                                 BitCodeFile wasm) {
        for (FilePart p : part.getParts()) {
            postRead((BitCodePart) p, wasm);
        }
        part.postRead(wasm);
    }

}
