package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U4;
import com.github.zxh.classpy.classfile.datatype.U1CpIndex;
import com.github.zxh.classpy.classfile.datatype.U4Hex;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.common.BytesReader;
import java.nio.ByteOrder;

/**
 * Convenience class for reading class files.
 * 
 * @author zxh
 */
public class ClassReader extends BytesReader {

    private ConstantPool constantPool;
    
    public ClassReader(byte[] bytes) {
        super(bytes, ByteOrder.BIG_ENDIAN, true);
    }
    
    public ConstantPool getConstantPool() {
        if (constantPool == null) {
            throw new FileParseException("ConstantPool is not ready!");
        } else {
            return constantPool;
        }
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
