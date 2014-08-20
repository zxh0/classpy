package com.github.zxh.classpy.classfile;

/**
 * Same as U1, but used as index of ConstantPool.
 *
 * @author zxh
 */
public class U1CpIndex extends ClassComponent {

    private int value;

    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        byte b = reader.getByteBuffer().get();
        value = Byte.toUnsignedInt(b);
        if (value > 0) {
            String constantDesc = reader.getConstantPool().getConstantDesc(value);
            setDesc("#" + value + "->" + constantDesc);
        } else {
            setDesc("#" + value);
        }
    }
    
}
