package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import java.util.function.Supplier;

/**
 * 
 * @param <E>
 * 
 * @author zxh
 */
public class SizeHeaderList<E extends DexComponent> extends DexComponent {

    private final Supplier<E> factory;
    private UInt size;
    private SizeKnownList<E> list;

    public SizeHeaderList(Supplier<E> factory) {
        this.factory = factory;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        size = reader.readUInt();
        list = reader.readSizeKnownList(size, factory);
    }
    
}
