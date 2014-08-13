package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;

/**
 *
 * @author zxh
 */
public class ConstantFloatInfo extends ConstantInfo {

    private U4 bytes;
    private float value;
    
    @Override
    public void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getFloat(reader.position());
        bytes = reader.readU4();
    }
    
}
