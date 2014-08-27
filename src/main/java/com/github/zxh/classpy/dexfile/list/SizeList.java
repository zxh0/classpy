package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * TODO better name.
 * @param <E>
 * @author zxh
 */
public class SizeList<E extends DexComponent> extends DexComponent {

    private final Supplier<E> factory;
    private UInt size;
    private DexList<E> list;

    public SizeList(Supplier<E> factory) {
        this.factory = factory;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        size = reader.readUInt();
        list = reader.readDexList(size, factory);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(size, list);
    }
    
}
