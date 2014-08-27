package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * DexComponent list.
 * 
 * @param <E> the type of elements in this list
 * 
 * @author zxh
 */
public class SizeKnownList<E extends DexComponent> extends DexComponent {

    private final int size;
    private final Supplier<E> factory;
    private final List<E> list;

    public SizeKnownList(int size, Supplier<E> factory) {
        this.size = size;
        this.factory = factory;
        this.list = new ArrayList<>();
    }
    
    @Override
    protected void readContent(DexReader reader) {
        readList(reader);
        setElementName();
    }
    
    private void readList(DexReader reader) {
        for (int i = 0; i < size; i++) {
            E e = factory.get();
            e.read(reader);
            list.add(e);
        }
    }
    
    private void setElementName() {
        for (int i = 0; i < size; i++) {
            E element = list.get(i);
            
            String newName = Util.formatIndex(size, i);
            String oldName = element.getName();
            if (oldName != null) {
                newName += " (" + oldName + ")";
            }
            element.setName(newName);
        }
    }
    
    @Override
    public List<E> getSubComponents() {
        return list;
    }
    
    public Stream<E> stream() {
        return list.stream();
    }
    
    public E get(int index) {
        return list.get(index);
    }
    
}
