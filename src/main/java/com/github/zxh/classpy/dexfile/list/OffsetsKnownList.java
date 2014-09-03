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
public class OffsetsKnownList<E extends DexComponent> extends DexList<E> {

    private final int[] offsets;
    private final Supplier<E> factory;

    public OffsetsKnownList(int[] offsets, Supplier<E> factory) {
        this.offsets = offsets;
        this.factory = factory;
    }
    
    @Override
    protected void readList(DexReader reader) {
        for (int offset : offsets) {
            E e = factory.get();
            reader.setPosition(offset);
            e.read(reader);
            list.add(e);
        }
    }
    
    @Override
    protected void setElementName() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            E element = list.get(i);
            String name = "#" + i + "(0x" + Integer.toHexString(element.getOffset()) + ")";
            element.setName(name);
        }
    }
    
}
