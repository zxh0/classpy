package com.github.zxh.classpy.lua.binarychunk.component;

import com.github.zxh.classpy.lua.binarychunk.BinaryChunkComponent;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;
import com.github.zxh.classpy.lua.binarychunk.datatype.LuByte;
import com.github.zxh.classpy.lua.binarychunk.datatype.LuaInt;
import com.github.zxh.classpy.lua.binarychunk.datatype.LuaNum;
import com.github.zxh.classpy.lua.binarychunk.datatype.LuaStr;
import com.github.zxh.classpy.lua.vm.LuaType;

/**
 * Lua constant.
 */
public class Constant extends BinaryChunkComponent {

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
            case LUA_TBOOLEAN:
                readBoolean(reader);
                break;
            case LUA_TNUMBER:
            case LUA_TNUMFLT:
                readNumber(reader);
                break;
            case LUA_TNUMINT:
                readInteger(reader);
                break;
            case LUA_TSHRSTR:
            case LUA_TLNGSTR:
            case LUA_TSTRING:
                readString(reader);
                break;
            default:
                throw new RuntimeException("todo:" + lt);
        }
    }

    private void readBoolean(BinaryChunkReader reader) {
        LuByte b = new LuByte();
        b.read(reader);
        super.add("k", b);
        super.setDesc(Boolean.toString(b.getValue() != 0));
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
