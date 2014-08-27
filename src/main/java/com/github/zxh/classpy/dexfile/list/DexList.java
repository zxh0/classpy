package com.github.zxh.classpy.dexfile.list;

import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @param <E>
 * 
 * @author zxh
 */
public abstract class DexList<E extends DexComponent> extends DexComponent {

    protected final List<E> list = new ArrayList<>();
    
    public E get(int index) {
        return list.get(index);
    }
    
    public Stream<E> stream() {
        return list.stream();
    }
    
    @Override
    protected final void readContent(DexReader reader) {
        readList(reader);
        setElementName();
    }
    
    protected abstract void readList(DexReader reader);
    
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
