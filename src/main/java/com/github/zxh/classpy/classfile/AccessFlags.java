package com.github.zxh.classpy.classfile;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Access and property flags of class, field, method and nested class.
 * 
 * @author zxh
 */
public class AccessFlags {
    
    private static final int TYPE_CLASS        = 0b0001;
    private static final int TYPE_FIELD        = 0b0010;
    private static final int TYPE_METHOD       = 0b0100;
    private static final int TYPE_NESTED_CLASS = 0b1000;
    private static final int TYPE_ALL          = 0b1111;
    
    private static enum Flags {
        
        ACC_PUBLIC      (0x0001, TYPE_ALL),
        ACC_PRIVATE     (0x0002, TYPE_FIELD | TYPE_METHOD | TYPE_NESTED_CLASS),
        ACC_PROTECTED   (0x0004, TYPE_FIELD | TYPE_METHOD | TYPE_NESTED_CLASS),
        ACC_STATIC      (0x0008, TYPE_FIELD | TYPE_METHOD | TYPE_NESTED_CLASS),
        ACC_FINAL       (0x0010, TYPE_ALL),
        ACC_SUPER       (0x0020, TYPE_CLASS),
        ACC_SYNCHRONIZED(0x0020, TYPE_METHOD),
        ACC_VOLATILE    (0x0040, TYPE_FIELD),
        ACC_BRIDGE      (0x0040, TYPE_METHOD),
        ACC_TRANSIENT   (0x0080, TYPE_FIELD),
        ACC_VARARGS     (0x0080, TYPE_METHOD),
        ACC_NATIVE      (0x0100, TYPE_METHOD),
        ACC_INTERFACE   (0x0200, TYPE_CLASS | TYPE_NESTED_CLASS),
        ACC_ABSTRACT    (0x0400, TYPE_CLASS | TYPE_METHOD | TYPE_NESTED_CLASS),
        ACC_STRICT      (0x0800, TYPE_METHOD),
        ACC_SYNTHETIC   (0x1000, TYPE_ALL),
        ACC_ANNOTATION  (0x2000, TYPE_CLASS | TYPE_NESTED_CLASS),
        ACC_ENUM        (0x4000, TYPE_CLASS | TYPE_FIELD | TYPE_NESTED_CLASS);
        
        private final int flag;
        private final int type;
        
        private Flags(int flag, int type) {
            this.flag = flag;
            this.type = type;
        }
        
    }
    
    public static <T extends ClassComponent & IntValue> void describeClassFlags(T flags) {
        flags.setDesc(describe(TYPE_CLASS, flags.getValue()));
    }
    
    public static <T extends ClassComponent & IntValue> void describeFieldFlags(T flags) {
        flags.setDesc(describe(TYPE_FIELD, flags.getValue()));
    }
    
    public static <T extends ClassComponent & IntValue> void describeMethodFlags(T flags) {
        flags.setDesc(describe(TYPE_METHOD, flags.getValue()));
    }
    
    public static <T extends ClassComponent & IntValue> void describeInnerClassFlags(T flags) {
        flags.setDesc(describe(TYPE_NESTED_CLASS, flags.getValue()));
    }
    
    public static <T extends ClassComponent & IntValue> void describeClassOrInnerClassFlags(T flags) {
        flags.setDesc(describe(TYPE_CLASS | TYPE_NESTED_CLASS, flags.getValue()));
    }
    
    private static String describe(int flagType, int flags) {
        return Stream.of(Flags.values())
                .filter(flag -> (flag.type & flagType) != 0 && (flag.flag & flags) != 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
    
}
