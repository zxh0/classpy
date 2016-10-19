package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.jvm.RefKind;

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
    protected String loadDesc(ConstantPool cp) {
        int referenceKind = super.getUInt("reference_kind");
        int referenceIndex = super.getUInt("reference_index");

        RefKind refKind = RefKind.valueOf(referenceKind);
        switch (refKind) {
            case REF_getField:
            case REF_getStatic:
            case REF_putField:
            case REF_putStatic:
                return refKind + "->" + cp.getFieldrefInfo(referenceIndex).loadDesc(cp);
            case REF_invokeVirtual:
            case REF_newInvokeSpecial:
                return refKind + "->" + cp.getMethodrefInfo(referenceIndex).loadDesc(cp);
            case REF_invokeStatic:
            case REF_invokeSpecial:
                try {
                    return refKind + "->" + cp.getMethodrefInfo(referenceIndex).loadDesc(cp);
                } catch (Exception e) {
                    return refKind + "->" + cp.getInterfaceMethodrefInfo(referenceIndex).loadDesc(cp);
                }
            case REF_invokeInterface:
                return refKind + "->" + cp.getInterfaceMethodrefInfo(referenceIndex).loadDesc(cp);
        }
        
        return null;
    }

}
