package com.github.zxh.classpy.lua.binarychunk;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileParser;

public class BinaryChunkParser implements FileParser {

    @Override
    public FileComponent parse(byte[] data) {
        BinaryChunkReader reader = new BinaryChunkReader(data);
        BinaryChunkFile bc = new BinaryChunkFile();
        bc.read(reader);
        postRead(bc);
        return bc;
    }

    private static void postRead(BinaryChunkComponent bc) {
        for (FileComponent c : bc.getComponents()) {
            postRead((BinaryChunkComponent) c);
        }
        bc.postRead();
    }

}
