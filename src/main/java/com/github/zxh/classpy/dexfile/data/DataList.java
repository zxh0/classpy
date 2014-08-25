package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
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

    private final Stream<Integer> offStream;
    private final Supplier<E> factory;
    private final List<E> list = new ArrayList<>();

    public DataList(Stream<Integer> offStream, Supplier<E> factory) {
        this.offStream = offStream;
        this.factory = factory;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        offStream.forEach(offset -> {
            reader.setPosition(offset);
            E e = factory.get();
            e.read(reader);
            list.add(e);
        });
    }

    @Override
    public List<E> getSubComponents() {
        return list;
    }
    
}
