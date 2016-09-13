package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}
*/
public class ConstantMethodHandleInfo extends ConstantInfo {

    {
        u1("reference_kind");
        u2("reference_index");
    }

    @Override
    protected String loadDesc(ConstantPool pool) {
        int referenceKind = super.getUInt("reference_kind");
        int referenceIndex = super.getUInt("reference_index");

        RefKind refKind = RefKind.valueOf(referenceKind);
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
    
}
