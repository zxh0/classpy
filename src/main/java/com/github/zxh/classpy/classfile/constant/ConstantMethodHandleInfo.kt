package com.github.zxh.classpy.classfile.constant;

/*
CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}
*/
class ConstantMethodHandleInfo : ConstantInfo() {

    init {
        u1("reference_kind");
        u2("reference_index");
    }

    override fun loadDesc(cp: ConstantPool): String {
        val referenceKind = super.getInt("reference_kind");
        val referenceIndex = super.getInt("reference_index");

        val refKind = RefKind.valueOf(referenceKind);
        return when (refKind) {
            RefKind.REF_getField,
            RefKind.REF_getStatic,
            RefKind.REF_putField,
            RefKind.REF_putStatic ->
                "$refKind->" + cp.getFieldrefInfo(referenceIndex).loadDesc(cp);
            RefKind.REF_invokeVirtual,
            RefKind.REF_newInvokeSpecial ->
                "$refKind->" + cp.getMethodrefInfo(referenceIndex).loadDesc(cp);
            RefKind.REF_invokeStatic,
            RefKind.REF_invokeSpecial ->
                try {
                    "$refKind->" + cp.getMethodrefInfo(referenceIndex).loadDesc(cp);
                } catch (e: Exception) {
                    "$refKind->" + cp.getInterfaceMethodrefInfo(referenceIndex).loadDesc(cp);
                }
            RefKind.REF_invokeInterface ->
                "$refKind->" + cp.getInterfaceMethodrefInfo(referenceIndex).loadDesc(cp);
            else -> "" // todo
        }
    }
    
}
