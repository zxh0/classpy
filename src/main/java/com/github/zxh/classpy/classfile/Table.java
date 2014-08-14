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
    }

    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(table);
    }
    
}
