package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Array of class components.
 * 
 * @param <E> the type of entry in this table
 * 
 * @author zxh
 */
public class Table<E extends ClassComponent> extends ClassComponent {

    private final Class<E> classOfE;
    private final int length;
    private E[] table;

    public Table(Class<E> classOfE, int n) {
        this.classOfE = classOfE;
        this.length = n;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        readTable(reader);
        setEntryName();
    }
    
    private void readTable(ClassReader reader) {
        //@SuppressWarnings("unchecked")
        table = (E[]) Array.newInstance(classOfE, length);
        
        try {
            for (int i = 0; i < length; i++) {
                if (classOfE == AttributeInfo.class) {
                    //@SuppressWarnings("unchecked")
                    E e = (E) readAttributeInfo(reader);
                    table[i] = e;
                } else {
                    table[i] = classOfE.newInstance();
                    table[i].read(reader);
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
    }
    
    private AttributeInfo readAttributeInfo(ClassReader reader) {
        int attrNameIndex = reader.getByteBuffer().getShort(reader.getPosition());
        String attrName = reader.getConstantPool().getUtf8String(attrNameIndex);
        
        AttributeInfo attr = AttributeInfo.create(attrName);
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
