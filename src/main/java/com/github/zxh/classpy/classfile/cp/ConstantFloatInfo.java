package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4Float;

/**
 *
 * @author zxh
 */
public class ConstantFloatInfo extends ConstantInfo {

    private U4Float floatValue;
    
    @Override
    public void readInfo(ClassReader reader) {
        floatValue = reader.readU4Float();
    }
    
}
