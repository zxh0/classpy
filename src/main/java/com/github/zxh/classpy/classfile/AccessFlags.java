package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Access and property flags of class, field, method and nested class.
 */
public class AccessFlags {
    
    private static final int AF_CLASS        = 0b0001;
    private static final int AF_FIELD        = 0b0010;
    private static final int AF_METHOD       = 0b0100;
    private static final int AF_NESTED_CLASS = 0b1000;
    private static final int AF_ALL          = 0b1111;
    
    private static enum Flags {
        
        ACC_PUBLIC      (0x0001, AF_ALL),
        ACC_PRIVATE     (0x0002, AF_FIELD | AF_METHOD | AF_NESTED_CLASS),
        ACC_PROTECTED   (0x0004, AF_FIELD | AF_METHOD | AF_NESTED_CLASS),
        ACC_STATIC      (0x0008, AF_FIELD | AF_METHOD | AF_NESTED_CLASS),
        ACC_FINAL       (0x0010, AF_ALL),
        ACC_SUPER       (0x0020, AF_CLASS),
        ACC_SYNCHRONIZED(0x0020, AF_METHOD),
        ACC_VOLATILE    (0x0040, AF_FIELD),
        ACC_BRIDGE      (0x0040, AF_METHOD),
        ACC_TRANSIENT   (0x0080, AF_FIELD),
        ACC_VARARGS     (0x0080, AF_METHOD),
        ACC_NATIVE      (0x0100, AF_METHOD),
        ACC_INTERFACE   (0x0200, AF_CLASS | AF_NESTED_CLASS),
        ACC_ABSTRACT    (0x0400, AF_CLASS | AF_METHOD | AF_NESTED_CLASS),
        ACC_STRICT      (0x0800, AF_METHOD),
        ACC_SYNTHETIC   (0x1000, AF_ALL),
        ACC_ANNOTATION  (0x2000, AF_CLASS | AF_NESTED_CLASS),
        ACC_ENUM        (0x4000, AF_CLASS | AF_FIELD | AF_NESTED_CLASS);
        
        private final int flag;
        private final int type;
        
        private Flags(int flag, int type) {
            this.flag = flag;
            this.type = type;
        }
        
    }
    
    public static void describeClassFlags(U2 flags) {
        flags.setDesc(describe(AF_CLASS, flags.getValue()));
    }
    
    public static void describeFieldFlags(U2 flags) {
        flags.setDesc(describe(AF_FIELD, flags.getValue()));
    }
    
    public static void describeMethodFlags(U2 flags) {
        flags.setDesc(describe(AF_METHOD, flags.getValue()));
    }
    
    public static void describeInnerClassFlags(U2 flags) {
        flags.setDesc(describe(AF_NESTED_CLASS, flags.getValue()));
    }
    
    public static void describeClassOrInnerClassFlags(U2 flags) {
        flags.setDesc(describe(AF_CLASS | AF_NESTED_CLASS, flags.getValue()));
    }
    
    private static String describe(int flagType, int flags) {
        return Stream.of(Flags.values())
                .filter(flag -> (flag.type & flagType) != 0 && (flag.flag & flags) != 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
    
}
