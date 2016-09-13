package com.github.zxh.classpy.classfile.reader;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U1CpIndex;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

import java.nio.ByteOrder;
import java.util.function.Supplier;

/**
 * Convenience class for reading class files.
 */
public class ClassReader extends BytesReader {

    private ConstantPool constantPool;

    public ClassReader(byte[] bytes) {
        super(bytes, ByteOrder.BIG_ENDIAN, true);
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
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

    public U2CpIndex readU2CpIndex() {
        return readCC(U2CpIndex::new);
    }

}
