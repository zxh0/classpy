package com.github.zxh.classpy.lua54.binarychunk;

import com.github.zxh.classpy.common.FileParser;
import com.github.zxh.classpy.common.FilePart;

public class BinaryChunkParser implements FileParser {

    @Override
    public FilePart parse(byte[] data) {
        BinaryChunkReader reader = new BinaryChunkReader(data);
        BinaryChunkFile bc = new BinaryChunkFile();
        try {
            bc.read(reader);
            postRead(bc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bc;
    }

    private static void postRead(BinaryChunkPart bc) {
        for (FilePart c : bc.getParts()) {
            postRead((BinaryChunkPart) c);
        }
        bc.postRead();
    }

}
