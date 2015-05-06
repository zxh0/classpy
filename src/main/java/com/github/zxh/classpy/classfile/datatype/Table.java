package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.helper.StringUtil;
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
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) Array.newInstance(classOfE, length);
        table = arr;
        
        try {
            for (int i = 0; i < length; i++) {
                table[i] = readEntry(reader);
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
    }
    
    private E readEntry(ClassReader reader) throws ReflectiveOperationException {
        if (classOfE == AttributeInfo.class) {
            @SuppressWarnings("unchecked")
            E e = (E) readAttributeInfo(reader);
            return e;
        } else {
            E e = classOfE.newInstance();
            e.read(reader);
            return e;
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
            String newName = StringUtil.formatIndex(length, i);
            String oldName = table[i].getName();
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
