package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 
 * @param <E>
 * 
 * @author zxh
 */
public class Uleb128HeaderList<E extends DexComponent> extends DexComponent {

    private final Supplier<E> factory;
    private Uleb128 size;
    private SizeKnownList<E> list;

    public Uleb128HeaderList(Supplier<E> factory) {
        this.factory = factory;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        size = reader.readUleb128();
        list = reader.readSizeKnownList(size, factory);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(size, list);
    }
    
}
