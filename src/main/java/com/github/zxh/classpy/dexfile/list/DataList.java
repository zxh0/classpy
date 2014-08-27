package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @param <E>
 * 
 * @author zxh
 */
public class DataList<E extends DexComponent> extends DexComponent {

    private final Stream<UInt> offStream;
    private final Supplier<E> factory;
    private final List<E> list = new ArrayList<>();

    public DataList(Stream<UInt> offStream, Supplier<E> factory) {
        this.offStream = offStream;
        this.factory = factory;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        readList(reader);
        setElementName();
    }
    
    private void readList(DexReader reader) {
        offStream.forEach(offset -> {
            reader.setPosition(offset);
            E e = factory.get();
            e.read(reader);
            list.add(e);
        });
    }
    
    // todo
    private void setElementName() {
        int size = list.size();
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
    
}
