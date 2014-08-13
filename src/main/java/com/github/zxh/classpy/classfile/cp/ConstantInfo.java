package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;

/*
cp_info {
    u1 tag;
    u1 info[];
}
 */
public abstract class ConstantInfo extends ClassComponent {

//    private ConstantType tag;
    private U1 tag;
    
    @Override
    protected final void readContent(ClassReader reader) {
        tag = reader.readU1();
        readInfo(reader);
    }
    
    protected abstract void readInfo(ClassReader reader);
    
    /**
     * Factory method.
     * @param tag
     * @return 
     */
    public static ConstantInfo create(byte tag) {
        switch (ConstantType.valueOf(tag)) {
            case CONSTANT_Integer: return new ConstantIntegerInfo();
            case CONSTANT_Float: return new ConstantFloatInfo();
            case CONSTANT_Long: return new ConstantLongInfo();
            case CONSTANT_Double: return new ConstantDoubleInfo();
            case CONSTANT_String: return new ConstantStringInfo();
            case CONSTANT_Utf8: return new ConstantUtf8Info();
            case CONSTANT_NameAndType: return new ConstantNameAndTypeInfo();
            case CONSTANT_Class: return new ConstantClassInfo();
            case CONSTANT_Fieldref: return new ConstantXXXrefInfo();
            case CONSTANT_Methodref: return new ConstantXXXrefInfo();
            case CONSTANT_InterfaceMethodref: return new ConstantXXXrefInfo();
            case CONSTANT_MethodHandle: return new ConstantMethodHandleInfo();
            case CONSTANT_MethodType: return new ConstantMethodTypeInfo();
            case CONSTANT_InvokeDynamic: return new ConstantInvokeDynamicInfo();
        }
        
        // unreachable code
        throw new RuntimeException("Should not throw this exception!");
    }
    
}
