package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import java.lang.reflect.Array;
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
        table = readArray(reader, classOfT, n);
        for (int i = 0; i < table.length; i++) {
            String oldName = table[i].getName();
            String newName = Util.formatIndex(n, i);
            if (oldName != null) {
                newName += " (" + oldName + ")";
            }
            table[i].setName(newName);
        }
    }

    private <T extends ClassComponent> T[] readArray(ClassReader reader,
            Class<T> classOfT, int n) {
        
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(classOfT, n);
        
        try {
            for (int i = 0; i < arr.length; i++) {
                if (classOfT == AttributeInfo.class) {
                    @SuppressWarnings("unchecked")
                    T t = (T) readAttributeInfo(reader);
                    arr[i] = t;
                } else {
                    arr[i] = classOfT.newInstance();
                    arr[i].read(reader);
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return arr;
    }
    
    private AttributeInfo readAttributeInfo(ClassReader reader) {
        int attributeNameIndex = reader.getByteBuffer().getShort(reader.getPosition());
        String attributeName = reader.getConstantPool().getUtf8String(attributeNameIndex);
        
        AttributeInfo attr = AttributeInfo.create(attributeName);
        attr.read(reader);
        
        return attr;
    }
    
    @Override
    public List<T> getSubComponents() {
        return Arrays.asList(table);
    }
    
}
