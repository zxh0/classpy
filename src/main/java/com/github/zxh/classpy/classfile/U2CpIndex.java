package com.github.zxh.classpy.classfile;

/**
 * Same as U2, but used as index of ConstantPool.
 *
 * @author zxh
 */
public class U2CpIndex extends ClassComponent {

    private int value;

    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        short s = reader.getByteBuffer().getShort();
        value = Short.toUnsignedInt(s);
        if (value > 0) {
            String constantDesc = reader.getConstantPool().getConstantDesc(value);
            setDesc("#" + value + "->" + constantDesc);
        } else {
            setDesc("#" + value);
        }
    }
    
}
