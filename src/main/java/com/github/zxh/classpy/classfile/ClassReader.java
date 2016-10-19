package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.common.BytesReader;

import java.nio.ByteOrder;

/**
 * Convenience class for reading class files.
 */
public class ClassReader extends BytesReader {

    private ConstantPool constantPool;

    public ClassReader(byte[] bytes) {
        super(bytes, ByteOrder.BIG_ENDIAN);
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

}
