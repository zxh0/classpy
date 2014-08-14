package com.github.zxh.classpy.classfile;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 * @param <T>
 */
public class Table<T extends ClassComponent> extends ClassComponent {

    private final Class<T> classOfT;
    private final int n;
    private T[] table;

    public Table(Class<T> classOfT, int n) {
        this.classOfT = classOfT;
        this.n = n;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        table = reader.readArray(classOfT, n);
        for (int i = 0; i < table.length; i++) {
            String oldName = table[i].getName();
            String newName = Util.formatIndex(n, i);
            if (oldName != null) {
                newName += " (" + oldName + ")";
            }
            table[i].setName(newName);
        }
    }

    @Override
    public List<T> getSubComponents() {
        return Arrays.asList(table);
    }
    
}
