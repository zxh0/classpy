package com.github.zxh.classpy.lua.binarychunk;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileParser;

public class BinaryChunkParser implements FileParser {

    @Override
    public FileComponent parse(byte[] data) {
        BinaryChunkReader reader = new BinaryChunkReader(data);
        BinaryChunkFile root = new BinaryChunkFile();
        root.read(reader);
        postRead(root);
        return root;
    }

    private static void postRead(BinaryChunkComponent parent) {
        for (FileComponent kid : parent.getComponents()) {
            postRead((BinaryChunkComponent) kid);
        }
        parent.postRead();
    }

}
