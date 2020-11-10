package com.github.zxh.classpy.lua54.binarychunk;

import com.github.zxh.classpy.lua54.binarychunk.datatype.LuByte;
import com.github.zxh.classpy.lua54.binarychunk.part.Function;
import com.github.zxh.classpy.lua54.binarychunk.part.Header;

/**
 * Represent lua binary chunk file.
 */
public class BinaryChunkFile extends BinaryChunkPart {

    // lua5.4.1/lundump.c#luaU_undump()
    {
        add("header",  new Header());
        add("nupvals", new LuByte());
        add("main",  new Function());
    }

}
