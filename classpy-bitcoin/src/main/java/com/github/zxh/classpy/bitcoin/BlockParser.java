package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.common.FileParser;

public class BlockParser implements FileParser {

    @Override
    public Block parse(byte[] data) {
        Block block = new Block();
        try {
            block.read(new BlockReader(data));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return block;
    }

}
