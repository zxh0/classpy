package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import java.util.function.Supplier;

/**
 * 
 * @param <E>
 * 
 * @author zxh
 */
public class SizeKnownList<E extends DexComponent> extends DexList<E> {

    private final int size;
    private final Supplier<E> factory;

    public SizeKnownList(int size, Supplier<E> factory) {
        this.size = size;
        this.factory = factory;
    }
    
    @Override
    protected void readList(DexReader reader) {
        for (int i = 0; i < size; i++) {
            E e = factory.get();
            e.read(reader);
            list.add(e);
        }
    }
    
}
