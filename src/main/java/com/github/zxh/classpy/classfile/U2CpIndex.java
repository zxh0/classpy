package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U2CpIndex extends ClassComponent {

//    private int value;
//
//    public int getValue() {
//        return value;
//    }
    
    @Override
    protected void readContent(ClassReader reader) {
        short s = reader.getByteBuffer().getShort();
        int constantIndex = Short.toUnsignedInt(s);
        String constantDesc = reader.getConstantPool().getConstantDesc(constantIndex);
        setDesc(constantIndex + "->" + constantDesc);
    }
    
}
