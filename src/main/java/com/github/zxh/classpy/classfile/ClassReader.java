package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attr.AttributeInfo;
import com.github.zxh.classpy.classfile.cp.ConstantInfo;
import com.github.zxh.classpy.classfile.cp.ConstantPool;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public class ClassReader {

    private final ByteBuffer buf;
    private ConstantPool constantPool;
    
    public ClassReader(ByteBuffer buf) {
        this.buf = buf;
    }
    
    public ByteBuffer getByteBuffer() {return buf;}
    public ConstantPool getConstantPool() {return constantPool;}
    
    public int getPosition() {
        return buf.position();
    }
    
    public U1 readU1() {
        U1 u1 = new U1();
        u1.read(this);
        return u1;
    }
    
    public U2 readU2() {
        U2 u2 = new U2();
        u2.read(this);
        return u2;
    }
    
    public U4 readU4() {
        U4 u4 = new U4();
        u4.read(this);
        return u4;
    }
    
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        buf.get(bytes);
        return bytes;
    }
    
    public ConstantPool readConstantPool(int cpCount) {
        constantPool = new ConstantPool(cpCount);
        constantPool.read(this);
        return constantPool;
    }
    
    public ConstantInfo readConstantInfo() {
        byte tag = buf.get(buf.position());
        
        ConstantInfo ci = ConstantInfo.create(tag);
        ci.read(this);
        
        return ci;
    }
    
    public AttributeInfo readAttributeInfo() {
        int attributeNameIndex = buf.getShort(buf.position());
        String attributeName = constantPool.getUtf8String(attributeNameIndex);
        
        AttributeInfo attr = AttributeInfo.create(attributeName);
        attr.read(this);
        
        return attr;
    }
    
    public <T extends ClassComponent> T[] readArray(Class<T> classOfT, int n) {
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(classOfT, n);
        
        try {
            for (int i = 0; i < arr.length; i++) {
                if (classOfT == AttributeInfo.class) {
                    @SuppressWarnings("unchecked")
                    T t = (T) readAttributeInfo();
                    arr[i] = t;
                } else {
                    arr[i] = classOfT.newInstance();
                    arr[i].read(this);
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return arr;
    }
    
    public <T extends ClassComponent> Table<T> readTable(Class<T> classOfT, int n) {
        Table<T> table = new Table<>(classOfT, n);
        table.read(this);
        return table;
    }
    
}
