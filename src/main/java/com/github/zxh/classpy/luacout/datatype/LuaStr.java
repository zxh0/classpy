package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * string in luac.out
 *
 * @see /lua/src/ldump.c#DumpString()
 */
public class LuaStr extends LuacOutComponent {

    @Override
    protected void readContent(LuacOutReader reader) {
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

    private void readStr(LuacOutReader reader, int bytesCount) {
        Bytes bytes = new Bytes(bytesCount);
        bytes.read(reader);
        super.add("bytes", bytes);

        String str = new String(bytes.getBytes()); // todo
        super.setDesc(str);
        bytes.setDesc(str);
    }

}
