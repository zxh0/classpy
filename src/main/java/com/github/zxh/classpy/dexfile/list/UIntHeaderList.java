package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 
 * @param <E>
 * 
 * @author zxh
 */
public class UIntHeaderList<E extends DexComponent> extends DexComponent {

    private final Supplier<E> factory;
    private UInt size;
    private SizeKnownList<E> list;

    public UIntHeaderList(Supplier<E> factory) {
        this.factory = factory;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        size = reader.readUInt();
        list = reader.readSizeKnownList(size, factory);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(size, list);
    }
    
}
