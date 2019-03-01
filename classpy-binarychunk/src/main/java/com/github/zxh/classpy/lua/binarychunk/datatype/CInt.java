package com.github.zxh.classpy.lua.binarychunk.datatype;

import com.github.zxh.classpy.lua.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;

/**
 * c int.
 *
 * @see /lua/src/ldump.c#DumpInt()
 */
public class CInt extends BinaryChunkPart {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        value = reader.readCInt();
        setDesc(Long.toString(value));
    }

}
