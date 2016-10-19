package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.ClassReader;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class UInt extends ClassComponent {

    protected static final Function<ClassReader, Integer> READ_U1 = ClassReader::readUnsignedByte;
    protected static final Function<ClassReader, Integer> READ_U2 = ClassReader::readUnsignedShort;
    protected static final Function<ClassReader, Integer> READ_U4 = ClassReader::readInt;

    protected static final BiFunction<Integer, ConstantPool, String> TO_STRING =
            (val, cp) -> val.toString();
    protected static final BiFunction<Integer, ConstantPool, String> TO_HEX =
            (val, cp) -> "0x" + Integer.toHexString(val).toUpperCase();
    protected static final BiFunction<Integer, ConstantPool, String> TO_CONST =
            (val, cp) -> val > 0
                    ? "#" + val + "->" + cp.getConstantDesc(val)
                    : "#" + val;


    private final Function<ClassReader, Integer> intReader;
    private final BiFunction<Integer, ConstantPool, String> intDescriber;
    private int value;

    public UInt(Function<ClassReader, Integer> intReader,
                BiFunction<Integer, ConstantPool, String> intDescriber) {
        this.intReader = intReader;
        this.intDescriber = intDescriber;
    }

    public final int getValue() {
        return value;
    }

    @Override
    protected final void readContent(ClassReader reader) {
        value = intReader.apply(reader);
    }

    @Override
    protected final void afterRead(ConstantPool cp) {
        setDesc(intDescriber.apply(value, cp));
    }
    
}
