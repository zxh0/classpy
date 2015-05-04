package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U4;
import com.github.zxh.classpy.classfile.datatype.U1CpIndex;
import com.github.zxh.classpy.classfile.datatype.U4Hex;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.ClassParseException;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U4Float;
import com.github.zxh.classpy.BytesReader;
import java.nio.ByteOrder;
import java.util.function.Supplier;

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
            throw new ClassParseException("ConstantPool is not ready!");
        } else {
            return constantPool;
        }
    }
    
    private <T extends ClassComponent> T readCC(Supplier<T> factory) {
        T cc = factory.get();
        cc.read(this);
        return cc;
    }
    
    public U1 readU1() {
        return readCC(U1::new);
    }
    
    public U1CpIndex readU1CpIndex() {
        return readCC(U1CpIndex::new);
    }
    
    public U2 readU2() {
        return readCC(U2::new);
    }
    
    public U2CpIndex readU2CpIndex() {
        return readCC(U2CpIndex::new);
    }
    
    public U4 readU4() {
        return readCC(U4::new);
    }
    
    public U4Float readU4Float() {
        return readCC(U4Float::new);
    }
    
    public U4Hex readU4Hex() {
        return readCC(U4Hex::new);
    }
    
    public ConstantPool readConstantPool(int cpCount) {
        constantPool = new ConstantPool(cpCount);
        constantPool.read(this);
        return constantPool;
    }
    
    public <T extends ClassComponent> Table<T> readTable(Class<T> classOfT, IntValue length) {
        Table<T> table = new Table<>(classOfT, length.getValue());
        table.read(this);
        return table;
    }
    
}
