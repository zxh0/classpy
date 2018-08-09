package com.github.zxh.classpy.classfile.jvm;

import static com.github.zxh.classpy.classfile.jvm.AccessFlagType.*;

/**
 * Access and property flags of class, field, method and nested class.
 */
public enum AccessFlags {

    ACC_PUBLIC      (0x0001, AF_ALL                                ),
    ACC_PRIVATE     (0x0002, AF_FIELD | AF_METHOD | AF_NESTED_CLASS),
    ACC_PROTECTED   (0x0004, AF_FIELD | AF_METHOD | AF_NESTED_CLASS),
    ACC_STATIC      (0x0008, AF_FIELD | AF_METHOD | AF_NESTED_CLASS),
    ACC_FINAL       (0x0010, AF_ALL                                ),
    ACC_SUPER       (0x0020, AF_CLASS                              ),
    ACC_TRANSITIVE  (0x0020, AF_MODULE_ATTR                        ),
    ACC_SYNCHRONIZED(0x0020, AF_METHOD                             ),
    ACC_VOLATILE    (0x0040, AF_FIELD                              ),
    ACC_BRIDGE      (0x0040, AF_METHOD                             ),
    ACC_STATIC_PHASE(0x0040, AF_MODULE_ATTR                        ),
    ACC_TRANSIENT   (0x0080, AF_FIELD                              ),
    ACC_VARARGS     (0x0080, AF_METHOD                             ),
    ACC_NATIVE      (0x0100, AF_METHOD                             ),
    ACC_INTERFACE   (0x0200, AF_CLASS | AF_NESTED_CLASS            ),
    ACC_ABSTRACT    (0x0400, AF_CLASS | AF_METHOD | AF_NESTED_CLASS),
    ACC_STRICT      (0x0800, AF_METHOD                             ),
    ACC_SYNTHETIC   (0x1000, AF_ALL                                ),
    ACC_ANNOTATION  (0x2000, AF_CLASS | AF_NESTED_CLASS            ),
    ACC_ENUM        (0x4000, AF_CLASS | AF_FIELD | AF_NESTED_CLASS ),
    ACC_MODULE      (0x8000, AF_CLASS                              ),
    ACC_MANDATED    (0x8000, AF_MODULE_ATTR                        ),
    ;
    
    public final int flag;
    public final int type;

    AccessFlags(int flag, int type) {
        this.flag = flag;
        this.type = type;
    }

}
