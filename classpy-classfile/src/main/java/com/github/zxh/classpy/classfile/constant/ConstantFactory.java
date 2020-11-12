package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.common.ParseException;

public class ConstantFactory {

    /**
     * Create concrete ConstantXxxInfo by tag.
     */
    public static ConstantInfo create(byte tag) {
        return switch (tag) {
            case 1 -> new ConstantUtf8Info();
            case 3 -> new ConstantIntegerInfo();
            case 4 -> new ConstantFloatInfo();
            case 5 -> new ConstantLongInfo();
            case 6 -> new ConstantDoubleInfo();
            case 7 -> new ConstantClassInfo();
            case 8 -> new ConstantStringInfo();
            case 9 -> new ConstantFieldrefInfo();
            case 10 -> new ConstantMethodrefInfo();
            case 11 -> new ConstantInterfaceMethodrefInfo();
            case 12 -> new ConstantNameAndTypeInfo();
            case 15 -> new ConstantMethodHandleInfo();
            case 16 -> new ConstantMethodTypeInfo();
            case 18 -> new ConstantInvokeDynamicInfo();
            case 19 -> new ConstantModuleInfo();
            case 20 -> new ConstantPackageInfo();
            default -> throw new ParseException("Invalid Constant Type: " + tag);
        };
    }

}
