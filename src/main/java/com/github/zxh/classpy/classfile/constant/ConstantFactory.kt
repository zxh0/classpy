package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassParseException;

object  ConstantFactory {

    /**
     * Create concrete ConstantXxxInfo by tag.
     * @param tag
     * @return
     */
    fun create(tag: Byte): ConstantInfo {
        when (tag.toInt()) {
             1 -> return ConstantUtf8Info();
             3 -> return ConstantIntegerInfo();
             4 -> return ConstantFloatInfo();
             5 -> return ConstantLongInfo();
             6 -> return ConstantDoubleInfo();
             7 -> return ConstantClassInfo();
             8 -> return ConstantStringInfo();
             9 -> return ConstantFieldrefInfo();
            10 -> return ConstantMethodrefInfo();
            11 -> return ConstantInterfaceMethodrefInfo();
            12 -> return ConstantNameAndTypeInfo();
            15 -> return ConstantMethodHandleInfo();
            16 -> return ConstantMethodTypeInfo();
            18 -> return ConstantInvokeDynamicInfo();
        }

        throw ClassParseException("Invalid Constant Type: " + tag);
    }

}
