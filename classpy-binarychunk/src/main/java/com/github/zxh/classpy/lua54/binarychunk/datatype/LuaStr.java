package com.github.zxh.classpy.lua54.binarychunk.datatype;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;

/**
 * string in binary chunk.
 */
public class LuaStr extends BinaryChunkPart {

    @Override
    protected void readContent(BinaryChunkReader reader) {
        VarInt size = new VarInt();
        size.read(reader);

        if (size.getValue() == 0) {
            super.setDesc("NULL");
        } else if (size.getValue() < 0xFF) {
            super.add("size", size);
            readStr(reader, size.getValue() - 1);
        }
    }

    private void readStr(BinaryChunkReader reader, int bytesCount) {
        Bytes bytes = new Bytes(bytesCount);
        bytes.read(reader);
        super.add("bytes", bytes);

        String str = new String(bytes.getBytes()); // todo
        super.setDesc(str);
        bytes.setDesc(str);
    }

}
