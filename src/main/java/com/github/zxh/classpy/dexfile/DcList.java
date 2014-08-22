package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.classfile.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DexComponents list.
 * 
 * @param <E> the type of elements in this list
 * 
 * @author zxh
 */
public class DcList<E extends DexComponent> extends DexComponent {

    private final Class<E> classOfE;
    private final int size;
    private final List<E> list;

    public DcList(Class<E> classOfE, int size) {
        this.classOfE = classOfE;
        this.size = size;
        this.list = new ArrayList<>();
    }
    
    @Override
    protected void readContent(DexReader reader) {
        readTable(reader);
//        setEntryName();
    }
    
    private void readTable(DexReader reader) {
        
        try {
            for (int i = 0; i < size; i++) {
                E e = classOfE.newInstance();
                list.add(e);
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
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
