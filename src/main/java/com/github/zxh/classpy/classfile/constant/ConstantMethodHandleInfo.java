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


    // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-5.html#jvms-5.4.3.5
    private enum RefKind {

        REF_getField(1),
        REF_getStatic(2),
        REF_putField(3),
        REF_putStatic(4),
        REF_invokeVirtual(5),
        REF_invokeStatic(6),
        REF_invokeSpecial(7),
        REF_newInvokeSpecial(8),
        REF_invokeInterface(9);

        public final int kind;

        private RefKind(int kind) {
            this.kind = kind;
        }

        public static RefKind valueOf(int kind) {
            for (RefKind value : values()) {
                if (value.kind == kind) {
                    return value;
                }
            }

            throw new IllegalArgumentException("Invalid RefKind: " + kind);
        }

    }

}
