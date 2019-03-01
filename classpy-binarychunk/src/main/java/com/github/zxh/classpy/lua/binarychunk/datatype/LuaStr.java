package com.github.zxh.classpy.lua.binarychunk.datatype;

import com.github.zxh.classpy.lua.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;

/**
 * string in binary chunk.
 *
 * @see /lua/src/ldump.c#DumpString()
 */
public class LuaStr extends BinaryChunkPart {

    @Override
    protected void readContent(BinaryChunkReader reader) {
        LuByte size = new LuByte();
        size.read(reader);

        if (size.getValue() == 0) {
            super.setDesc("NULL");
        } else if (size.getValue() < 0xFF) {
            super.add("size", size);
            readStr(reader, size.getValue() - 1);
        } else { // size == 0xFF
            CSizet xsize = new CSizet();
            xsize.read(reader);
            super.add("size", xsize);
            readStr(reader, (int) xsize.getValue() - 1);
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
