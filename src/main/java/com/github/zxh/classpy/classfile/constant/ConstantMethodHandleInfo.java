package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}
*/
public class ConstantMethodHandleInfo extends ConstantInfo {

    private U1 referenceKind;
    private U2 referenceIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        referenceKind = reader.readU1();
        referenceIndex = reader.readU2();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        RefKind refKind = RefKind.valueOf(referenceKind.getValue());
        switch (refKind) {
            case REF_getField:
            case REF_getStatic:
            case REF_putField:
            case REF_putStatic:
                return refKind + "->" + pool.getFieldrefInfo(referenceIndex).loadDesc(pool);
            case REF_invokeVirtual:
            case REF_newInvokeSpecial:
                return refKind + "->" + pool.getMethodrefInfo(referenceIndex).loadDesc(pool);
            case REF_invokeStatic:
            case REF_invokeSpecial:
                try {
                    return refKind + "->" + pool.getMethodrefInfo(referenceIndex).loadDesc(pool); 
                } catch (Exception e) {
                    return refKind + "->" + pool.getInterfaceMethodrefInfo(referenceIndex).loadDesc(pool);
                }
            case REF_invokeInterface:
                return refKind + "->" + pool.getInterfaceMethodrefInfo(referenceIndex).loadDesc(pool);
        }
        
        return null;
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, referenceKind, referenceIndex);
    }
    
}
