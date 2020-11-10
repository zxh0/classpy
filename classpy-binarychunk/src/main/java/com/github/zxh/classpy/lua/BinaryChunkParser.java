package com.github.zxh.classpy.lua;

import com.github.zxh.classpy.common.FileParser;
import com.github.zxh.classpy.common.FilePart;

public class BinaryChunkParser implements FileParser {

    private final com.github.zxh.classpy.lua53.binarychunk.BinaryChunkParser lua53BCParser = new com.github.zxh.classpy.lua53.binarychunk.BinaryChunkParser();
    private final com.github.zxh.classpy.lua54.binarychunk.BinaryChunkParser lua54BCParser = new com.github.zxh.classpy.lua54.binarychunk.BinaryChunkParser();

    @Override
    public FilePart parse(byte[] data) {
        if (data.length > 6 && data[4] == 0x54) {
            return lua54BCParser.parse(data);
        } else {
            return lua53BCParser.parse(data);
        }
    }

}
