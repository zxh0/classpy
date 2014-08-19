package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 
 * @param <E> the type of entry in this table
 * 
 * @author zxh
 */
public class Table<E extends ClassComponent> extends ClassComponent {

    private final Class<E> classOfT;
    private final int length;
    private E[] table;

    public Table(Class<E> classOfT, int n) {
        this.classOfT = classOfT;
        this.length = n;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        table = readTable(reader);
        setEntryName();
    }
    
    private E[] readTable(ClassReader reader) {
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) Array.newInstance(classOfT, length);
        
        try {
            for (int i = 0; i < arr.length; i++) {
                if (classOfT == AttributeInfo.class) {
                    @SuppressWarnings("unchecked")
                    E t = (E) readAttributeInfo(reader);
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
    
    private void setEntryName() {
        for (int i = 0; i < table.length; i++) {
            String oldName = table[i].getName();
            String newName = Util.formatIndex(length, i);
            if (oldName != null) {
                newName += " (" + oldName + ")";
            }
            table[i].setName(newName);
        }
    }
    
    @Override
    public List<E> getSubComponents() {
        return Arrays.asList(table);
    }
    
}
