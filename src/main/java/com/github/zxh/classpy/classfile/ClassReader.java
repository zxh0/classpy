package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.constant.ConstantInfo;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;

/**
 * Convenience class for reading class files.
 * 
 * @author zxh
 */
public class ClassReader {

    private final ByteBuffer buf;
    private ConstantPool constantPool;
    
    public ClassReader(byte[] bytes) {
        this.buf = ByteBuffer.wrap(bytes);
    }
    
    public ByteBuffer getByteBuffer() {
        return buf;
    }
    
    public int getPosition() {
        return buf.position();
    }
    
    public ConstantPool getConstantPool() {
        if (constantPool == null) {
            throw new ClassParseException("ConstantPool is not ready!");
        } else {
            return constantPool;
        }
    }
    
    public void skipBytes(int n) {
        for (int i = 0; i < n; i++) {
            buf.get();
        }
    }
    
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        buf.get(bytes);
        return bytes;
    }
    
    public U1 readU1() {
        U1 u1 = new U1();
        u1.read(this);
        return u1;
    }
    
    public U1CpIndex readU1CpIndex() {
        U1CpIndex u1 = new U1CpIndex();
        u1.read(this);
        return u1;
    }
    
    public U2 readU2() {
        U2 u2 = new U2();
        u2.read(this);
        return u2;
    }
    
    public U2CpIndex readU2CpIndex() {
        U2CpIndex u2 = new U2CpIndex();
        u2.read(this);
        return u2;
    }
    
    public U4 readU4() {
        U4 u4 = new U4();
        u4.read(this);
        return u4;
    }
    
    public U4Hex readU4Hex() {
        U4Hex u4 = new U4Hex();
        u4.read(this);
        return u4;
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
    
    // todo
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
    
    private AttributeInfo readAttributeInfo() {
        int attributeNameIndex = buf.getShort(buf.position());
        String attributeName = constantPool.getUtf8String(attributeNameIndex);
        
        AttributeInfo attr = AttributeInfo.create(attributeName);
        attr.read(this);
        
        return attr;
    }
    
    public <T extends ClassComponent> Table<T> readTable(Class<T> classOfT, U1 length) {
        return readTable(classOfT, length.getValue());
    }
    
    public <T extends ClassComponent> Table<T> readTable(Class<T> classOfT, U2 length) {
        return readTable(classOfT, length.getValue());
    }
    
    private <T extends ClassComponent> Table<T> readTable(Class<T> classOfT, int length) {
        Table<T> table = new Table<>(classOfT, length);
        table.read(this);
        return table;
    }
    
}
