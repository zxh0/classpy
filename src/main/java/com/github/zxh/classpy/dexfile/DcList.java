package com.github.zxh.classpy.dexfile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * DexComponents list.
 * 
 * @param <E> the type of elements in this list
 * 
 * @author zxh
 */
public class DcList<E extends DexComponent> extends DexComponent {

    private final int size;
    private final Supplier<E> factory;
    private final List<E> list;

    public DcList(int size, Supplier<E> factory) {
        this.size = size;
        this.factory = factory;
        this.list = new ArrayList<>();
    }
    
    @Override
    protected void readContent(DexReader reader) {
        readTable(reader);
//        setEntryName();
    }
    
    private void readTable(DexReader reader) {
        for (int i = 0; i < size; i++) {
            E e = factory.get();
            e.read(reader);
            list.add(e);
        }
    }
    
//    
//    private void setEntryName() {
//        for (int i = 0; i < table.length; i++) {
//            String newName = Util.formatIndex(length, i);
//            String oldName = table[i].getName();
//            if (oldName != null) {
//                newName += " (" + oldName + ")";
//            }
//            table[i].setName(newName);
//        }
//    }
//    
    @Override
    public List<? extends DexComponent> getSubComponents() {
        return list;
    }
    
}
