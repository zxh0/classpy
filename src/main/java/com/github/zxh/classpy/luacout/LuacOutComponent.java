package com.github.zxh.classpy.luacout;

import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.luacout.datatype.*;

import java.util.function.Supplier;

/**
 * luac.out component.
 */
public class LuacOutComponent extends BytesComponent {

    public final void read(LuacOutReader reader) {
        int offset = reader.getPosition();
        readContent(reader);
        int length = reader.getPosition() - offset;
        super.setOffset(offset);
        super.setLength(length);
    }

    protected void readContent(LuacOutReader reader) {
        for (BytesComponent bc : getComponents()) {
            ((LuacOutComponent) bc).read(reader);
        }
    }

    protected void afterRead() {

    }

    public void lu_byte(String name) {
        super.add(name, new LuByte());
    }

    public void lua_int(String name) {
        super.add(name, new LuaInt());
    }

    public void cint(String name) {
        super.add(name, new CInt());
    }

    public void lua_num(String name) {
        super.add(name, new LuaNum());
    }

    public void literal(String name, int bytesCount) {
        super.add(name, new Literal(bytesCount));
    }

    public void str(String name) {
        super.add(name, new LuaStr());
    }

    public void bytes(String name, int n) {
        super.add(name, new Bytes(n));
    }

    public void table(String name, Supplier<LuacOutComponent> componentSupplier) {
        super.add(name, new Table(componentSupplier));
    }

}
