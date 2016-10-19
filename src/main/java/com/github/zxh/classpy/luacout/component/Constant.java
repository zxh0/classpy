package com.github.zxh.classpy.luacout.component;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;
import com.github.zxh.classpy.luacout.datatype.LuByte;
import com.github.zxh.classpy.luacout.datatype.LuaInt;
import com.github.zxh.classpy.luacout.datatype.LuaNum;
import com.github.zxh.classpy.luacout.datatype.LuaStr;
import com.github.zxh.classpy.luacout.lvm.LuaType;

/**
 * Lua constant.
 */
public class Constant extends LuacOutComponent {

    @Override
    protected void readContent(LuacOutReader reader) {
        int t = readHead(reader);
        readBody(reader, t);
    }

    private int readHead(LuacOutReader reader) {
        LuByte t = new LuByte();
        t.read(reader);
        super.add("t", t);
        return t.getValue();
    }

    private void readBody(LuacOutReader reader, int type) {
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

    private void readBoolean(LuacOutReader reader) {
        LuByte b = new LuByte();
        b.read(reader);
        super.add("k", b);
        super.setDesc(Boolean.toString(b.getValue() != 0));
    }

    private void readNumber(LuacOutReader reader) {
        LuaNum d = new LuaNum();
        d.read(reader);
        super.add("k", d);
        super.setDesc(d.getDesc());
    }

    private void readInteger(LuacOutReader reader) {
        LuaInt i = new LuaInt();
        i.read(reader);
        super.add("k", i);
        super.setDesc(i.getDesc());
    }

    private void readString(LuacOutReader reader) {
        LuaStr str = new LuaStr();
        str.read(reader);
        super.add("k", str);
        super.setDesc(str.getDesc());
    }

}
