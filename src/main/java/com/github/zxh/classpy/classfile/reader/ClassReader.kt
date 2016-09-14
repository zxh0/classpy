package com.github.zxh.classpy.classfile.reader;

import com.github.zxh.classpy.classfile.constant.ConstantPool;

/**
 * Convenience class for reading class files.
 */
public class ClassReader extends BytesReader {

    private ConstantPool constantPool;

    public ClassReader(byte[] bytes) {
        super(bytes);
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

}
