package com.github.zxh.classpy.classfile.constant;

/**
 * http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-5.html#jvms-5.4.3.5
 */
enum class RefKind(val kind: Int) {
    
    REF_getField(1),
    REF_getStatic(2),
    REF_putField(3),
    REF_putStatic(4),
    REF_invokeVirtual(5),
    REF_invokeStatic(6),
    REF_invokeSpecial(7),
    REF_newInvokeSpecial(8),
    REF_invokeInterface(9);

    companion object XXX {
        fun valueOf(kind: Int): RefKind {
            for (value in values()) {
                if (value.kind == kind) {
                    return value;
                }
            }

            throw IllegalArgumentException("Invalid RefKind: " + kind);
        }
    }
    
}
