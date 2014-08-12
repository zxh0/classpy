package com.github.zxh.classpy.classfile.cp;

/**
 *
 * @author zxh
 */
public enum ConstantType {
    
    CONSTANT_Class(7),
    CONSTANT_Fieldref(9),
    CONSTANT_Methodref(10),
    CONSTANT_InterfaceMethodref(11),
    CONSTANT_String(8),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_NameAndType(12),
    CONSTANT_Utf8(1),
    CONSTANT_MethodHandle(15),
    CONSTANT_MethodType(16),
    CONSTANT_InvokeDynamic(18);
    
    public final int type;

    private ConstantType(int type) {
        this.type = type;
    }
    
    
    
}
