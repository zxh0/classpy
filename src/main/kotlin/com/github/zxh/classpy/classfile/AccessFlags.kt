package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Access and property flags of class, field, method and nested class.
 */
object AccessFlags {
    private val AF_CLASS = 1
    private val AF_FIELD = 2
    private val AF_METHOD = 4
    private val AF_NESTED_CLASS = 8
    private val AF_ALL = 15
    private enum class Flags private constructor(flag:Int, type:Int) {
        ACC_PUBLIC(0x0001, AF_ALL),
        ACC_PRIVATE(0x0002, AF_FIELD or AF_METHOD or AF_NESTED_CLASS),
        ACC_PROTECTED(0x0004, AF_FIELD or AF_METHOD or AF_NESTED_CLASS),
        ACC_STATIC(0x0008, AF_FIELD or AF_METHOD or AF_NESTED_CLASS),
        ACC_FINAL(0x0010, AF_ALL),
        ACC_SUPER(0x0020, AF_CLASS),
        ACC_SYNCHRONIZED(0x0020, AF_METHOD),
        ACC_VOLATILE(0x0040, AF_FIELD),
        ACC_BRIDGE(0x0040, AF_METHOD),
        ACC_TRANSIENT(0x0080, AF_FIELD),
        ACC_VARARGS(0x0080, AF_METHOD),
        ACC_NATIVE(0x0100, AF_METHOD),
        ACC_INTERFACE(0x0200, AF_CLASS or AF_NESTED_CLASS),
        ACC_ABSTRACT(0x0400, AF_CLASS or AF_METHOD or AF_NESTED_CLASS),
        ACC_STRICT(0x0800, AF_METHOD),
        ACC_SYNTHETIC(0x1000, AF_ALL),
        ACC_ANNOTATION(0x2000, AF_CLASS or AF_NESTED_CLASS),
        ACC_ENUM(0x4000, AF_CLASS or AF_FIELD or AF_NESTED_CLASS);
        val flag:Int = flag
        val type:Int = type
    }
    fun describeClassFlags(flags:U2) {
        flags.desc = describe(AF_CLASS, flags.value)
    }
    fun describeFieldFlags(flags:U2) {
        flags.desc = describe(AF_FIELD, flags.value)
    }
    fun describeMethodFlags(flags:U2) {
        flags.desc = describe(AF_METHOD, flags.value)
    }
    fun describeInnerClassFlags(flags:U2) {
        flags.desc = describe(AF_NESTED_CLASS, flags.value)
    }
    fun describeClassOrInnerClassFlags(flags:U2) {
        flags.desc = describe(AF_CLASS or AF_NESTED_CLASS, flags.value)
    }
//    private static String describe(int flagType, int flags) {
//        return Stream.of(Flags.values())
//                .filter(flag -> (flag.type & flagType) != 0 && (flag.flag & flags) != 0)
//        .map(Object::toString)
//                .collect(Collectors.joining(", "));
//    }
    private fun describe(flagType:Int, flags:Int):String {
        return Flags.values()
                .filter { it.type and flagType != 0 && it.flag and flags != 0 }
                .map { it.toString() }
                .joinToString(separator = ", ")
    }
}