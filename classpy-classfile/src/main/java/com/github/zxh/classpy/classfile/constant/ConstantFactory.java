package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.common.ParseException;

public class ConstantFactory {

    /**
     * Create concrete ConstantXxxInfo by tag.
     * @param tag
     * @return
     */
    public static ConstantInfo create(byte tag) {
        switch (tag) {
            case  1: return new ConstantUtf8Info();
            case  3: return new ConstantIntegerInfo();
            case  4: return new ConstantFloatInfo();
            case  5: return new ConstantLongInfo();
            case  6: return new ConstantDoubleInfo();
            case  7: return new ConstantClassInfo();
            case  8: return new ConstantStringInfo();
            case  9: return new ConstantFieldrefInfo();
            case 10: return new ConstantMethodrefInfo();
            case 11: return new ConstantInterfaceMethodrefInfo();
            case 12: return new ConstantNameAndTypeInfo();
            case 15: return new ConstantMethodHandleInfo();
            case 16: return new ConstantMethodTypeInfo();
            case 18: return new ConstantInvokeDynamicInfo();
            case 19: return new ConstantModuleInfo();
            case 20: return new ConstantPackageInfo();
        }

        throw new ParseException("Invalid Constant Type: " + tag);
    }

}
