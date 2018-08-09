package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.jvm.AccessFlags;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class U2AccessFlags extends UInt {

    public U2AccessFlags(int afType) {
        super(READ_U2, (val, cp) -> describe(afType, val));
    }

    private static String describe(int flagsType, int flagsVal) {
        return Stream.of(AccessFlags.values())
                .filter(flag -> (flag.type & flagsType) != 0)
                .filter(flag -> (flag.flag & flagsVal) != 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

}
