package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ConstantPool extends ClassComponent {
    
    private final int cpCount;
    private final List<ConstantInfo> constants;

    public ConstantPool(int cpCount) {
        this.cpCount = cpCount;
        constants = new ArrayList<>(cpCount);
    }
    
    @Override
    public void readContent(ClassReader reader) {
        for (int i = 0; i < cpCount; i++) {
            constants.add(readConstantInfo(reader));
        }
    }
    
    private static ConstantInfo readConstantInfo(ClassReader reader) {
        int idx = reader.position();
        byte tag = reader.getByteBuffer().get(idx);
        
        ConstantInfo ci = createConstantInfo(tag);
        ci.read(reader);
        
        return ci;
    }
    
    private static ConstantInfo createConstantInfo(byte tag) {
        ConstantType ct = getConstantType(tag);
        
        // todo
        switch (ct) {
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
            case CONSTANT_InvokeDynamic:
        }
        
        // unreachable code
        throw new ClassParseException("Invalid constant type: " + tag);
    }
    
    private static ConstantType getConstantType(int type) {
        for (ConstantType ct : ConstantType.values()) {
            if (ct.type == type) {
                return ct;
            }
        }
        throw new ClassParseException("Invalid constant type: " + type);
    }
    
}
