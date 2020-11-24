package com.github.zxh.classpy.common;

public abstract class FixedInt<R extends BytesReader> extends ReadableFilePart<R> {

    public enum IntType { I8, I16, I32, I64, U8, U16, U32, U64 }
    public enum IntDesc { Dec, Hex }

    private final IntType intType;
    private final IntDesc intDesc;
    private long value;

    public FixedInt(IntType intType,
                    IntDesc intDesc) {
        this.intType = intType;
        this.intDesc = intDesc;
    }

    public final long getValue() {
        return value;
    }
    public final int getIntValue() {
        return (int) value;
    }

    @Override
    protected final void readContent(R reader) {
        value = switch (intType) {
            case I8  -> reader.readFixedI8();
            case U8  -> reader.readFixedU8();
            case I16 -> reader.readFixedI16();
            case U16 -> reader.readFixedU16();
            case I32 -> reader.readFixedI32();
            case U32 -> reader.readFixedU32();
            case I64, U64 -> reader.readFixedI64();
        };
        switch (intDesc) {
            case Dec -> setDesc(isUnsigned()
                    ? Long.toUnsignedString(value)
                    : Long.toString(value));
            case Hex -> setDesc("0x" + Long.toHexString(value).toUpperCase());
        }
    }

    private boolean isUnsigned() {
        return switch (intType) {
            case U8, U16, U32, U64 -> true;
            default -> false;
        };
    }

}
