package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/**
 *
 * @author zxh
 */
public class ConstantStringInfo extends ConstantInfo {

    private U2 stringIndex;
    
    @Override
    public void readInfo(ClassReader reader) {
        stringIndex = reader.readU2();
    }
    
}
