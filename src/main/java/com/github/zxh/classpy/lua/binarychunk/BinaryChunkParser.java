package com.github.zxh.classpy.lua.binarychunk;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.BytesParser;

public class BinaryChunkParser implements BytesParser {

    @Override
    public FileComponent parse(byte[] bytes) {
        BinaryChunkReader reader = new BinaryChunkReader(bytes);
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
