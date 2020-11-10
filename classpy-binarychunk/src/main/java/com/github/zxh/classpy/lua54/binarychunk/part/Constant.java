package com.github.zxh.classpy.lua54.binarychunk.part;

import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkReader;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuByte;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuaInt;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuaNum;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuaStr;
import com.github.zxh.classpy.lua54.vm.LuaType;

/**
 * Lua constant.
 */
public class Constant extends BinaryChunkPart {

    @Override
    protected void readContent(BinaryChunkReader reader) {
        int t = readHead(reader);
        readBody(reader, t);
    }

    private int readHead(BinaryChunkReader reader) {
        LuByte t = new LuByte();
        t.read(reader);
        super.add("t", t);
        return t.getValue();
    }

    private void readBody(BinaryChunkReader reader, int type) {
        LuaType lt = LuaType.valueOf(type);
        super.setName(lt.name());
        switch (lt) {
            case LUA_TNIL:
                break;
            case LUA_VFALSE:
                break;
            case LUA_VTRUE:
                break;
            case LUA_TNUMBER:
            case LUA_VNUMFLT:
                readNumber(reader);
                break;
            case LUA_VNUMINT:
                readInteger(reader);
                break;
            case LUA_TSTRING:
            case LUA_VSHRSTR:
            case LUA_VLNGSTR:
                readString(reader);
                break;
            default:
                throw new RuntimeException("TODO:" + lt);
        }
    }

    private void readNumber(BinaryChunkReader reader) {
        LuaNum d = new LuaNum();
        d.read(reader);
        super.add("k", d);
        super.setDesc(d.getDesc());
    }

    private void readInteger(BinaryChunkReader reader) {
        LuaInt i = new LuaInt();
        i.read(reader);
        super.add("k", i);
        super.setDesc(i.getDesc());
    }

    private void readString(BinaryChunkReader reader) {
        LuaStr str = new LuaStr();
        str.read(reader);
        super.add("k", str);
        super.setDesc(str.getDesc());
    }

}
