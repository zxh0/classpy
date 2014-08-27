package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @param <E>
 * 
 * @author zxh
 */
public class OffsetsKnownList<E extends DexComponent> extends DexList<E> {

    private final Stream<UInt> offStream;
    private final Supplier<E> factory;

    public OffsetsKnownList(Stream<UInt> offStream, Supplier<E> factory) {
        this.offStream = offStream;
        this.factory = factory;
    }
    
    @Override
    protected void readList(DexReader reader) {
        offStream.forEach(offset -> {
            reader.setPosition(offset);
            E e = factory.get();
            e.read(reader);
            list.add(e);
        });
    }
    
}
