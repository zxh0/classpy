package com.github.zxh.classpy.lua.binarychunk;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.common.FileParser;

public class BinaryChunkParser implements FileParser {

    @Override
    public FilePart parse(byte[] data) {
        BinaryChunkReader reader = new BinaryChunkReader(data);
        BinaryChunkFile bc = new BinaryChunkFile();
        bc.read(reader);
        postRead(bc);
        return bc;
    }

    private static void postRead(BinaryChunkPart bc) {
        for (FilePart c : bc.getParts()) {
            postRead((BinaryChunkPart) c);
        }
        bc.postRead();
    }

}
